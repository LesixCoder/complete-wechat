package com.lunchtasting.server.biz.seller.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.seller.SellerCommentBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.orders.OrdersDAO;
import com.lunchtasting.server.dao.seller.SellerCommentDAO;
import com.lunchtasting.server.enumeration.StateEnum;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.po.lt.SellerComment;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class SellerCommentBIZImpl implements SellerCommentBIZ {

	@Autowired
	private SellerCommentDAO sellerCommentDAO;
	@Autowired
	private OrdersDAO ordersDAO;

	@Override
	public Integer getSellerCommentCount(Long sellerId) {
		return sellerCommentDAO.getSellerCommentCount(sellerId);
	}

	@Override
	public List querySellerCommentList(Long sellerId, Integer page,
			Integer pageSize) {
		List list = sellerCommentDAO.querySellerCommentList(sellerId, page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 图片
				 */
				if(ValidatorHelper.isMapNotEmpty(map.get("img_array"))){
					List imgList = new ArrayList();
					String imgArray = map.get("img_array").toString();
					for(String img : imgArray.split(",")){
						imgList.add(SysConfig.IMG_VISIT_URL+img
								+ QiNiuStorageHelper.MODEL0+"w/640/h/500");
					}
					map.put("img_list", imgList);
					map.remove("img_array");
				}
				
				/**
				 * 用户图片
				 */
				if(ValidatorHelper.isMapNotEmpty(map.get("user_img_url"))){
					map.put("user_img_url", SysConfig.IMG_VISIT_URL+map.get("user_img_url")
							+ QiNiuStorageHelper.MODEL1+"w/200/h/200");
				}
				
				
				newList.add(map);
			}
			return newList;
		}
		return list;
	}

	@Override
	public Boolean createComment(Long sellerId, Long userId,Long orderId, Integer score,
			String content, String imgArray) throws Exception {
		
		SellerComment comment = new SellerComment();
		comment.setId(IdWorker.getId());
		comment.setSellerId(sellerId);
		comment.setUserId(userId);
		comment.setScore(score);
		comment.setContent(content);
		comment.setImgArray(imgArray);
		sellerCommentDAO.create(comment);		
		
		/**
		 * 修改订单状态
		 */
		ordersDAO.updateStatus(orderId, StateEnum.ORDER_ISCOMMENT.getValue());
		
		return true;
	}
}
