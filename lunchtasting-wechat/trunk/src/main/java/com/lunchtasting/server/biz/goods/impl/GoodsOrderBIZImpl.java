package com.lunchtasting.server.biz.goods.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.goods.GoodsOrderBIZ;
import com.lunchtasting.server.dao.goods.GoodsOrderDAO;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.po.lt.goods.GoodsOrder;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class GoodsOrderBIZImpl implements GoodsOrderBIZ {

	@Autowired
	private GoodsOrderDAO goodsOrderDAO;

	@Override
	public GoodsOrder getById(Long id) {
		return goodsOrderDAO.getById(id);
	}
	
	@Override
	public List queryOrderList(Long userId, Integer status, Integer page,
			Integer pageSize) {
		List list = goodsOrderDAO.queryOrderList(userId, status, page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 拆分订单清单列表
				 */
				List goodsList = null;
				String imgUrl = null;
				int goodsCount = 0;
				if(ValidatorHelper.isNotEmpty(map.get("goods_concat_str"))){
					goodsList = new ArrayList();
					String concatStr = map.get("goods_concat_str").toString();
					String [] aSpilt = concatStr.split("\\|");
					if(ValidatorHelper.isNotEmpty(aSpilt)){
						Map goodsMap = null;
						for(String s : aSpilt){
							String [] bSpilt = s.split("\\@");
							if(ValidatorHelper.isNotEmpty(bSpilt)){
								
								goodsCount = goodsCount+1;
								
//								/**
//								 * 封面图片
//								 */
//								if(goodsCount == 1){
//									imgUrl = bSpilt[0];
//								}
								
								/**
								 * 商品信息
								 * 只添加三个
								 */
								if(goodsCount<4){
									goodsMap = new HashMap();
									goodsMap.put("img_url", VariableHelper.IMAGE_VISIT_URL+bSpilt[0]);
									goodsMap.put("name", bSpilt[1]);
									goodsMap.put("property", bSpilt[2]);
									goodsMap.put("number", bSpilt[3]);
									goodsList.add(goodsMap);
								}
							}
						}
					}
				}
				map.remove("goods_concat_str");
//				map.put("img_url", VariableHelper.IMAGE_VISIT_URL + imgUrl);
				map.put("goods_count", goodsCount);
				map.put("goods_list", goodsList);
				newList.add(map);
				
			}
			return newList;
		}
		return list;
	}

	@Override
	public Integer getOrderCount(Long userId, Integer status) {
		return goodsOrderDAO.getOrderCount(userId, status);
	}

	@Override
	public Map getOrderDetail(Long orderId,Long userId) {
		return goodsOrderDAO.getOrderDetail(orderId, userId);
	}

	@Override
	public Long createGoodsOrder(Long userId, Long addressId,
			Long goodsSkuId,Double price,String code,String remark) throws Exception {
		
		IdWorker idWorker = new IdWorker(0, 0);
		
		/**
		 * 创建订单
		 */
		GoodsOrder goodsOrder = new GoodsOrder();
		long orderId = idWorker.nextId();
		goodsOrder.setId(orderId);
		goodsOrder.setAddressId(addressId);
		goodsOrder.setUserId(userId);
		goodsOrder.setPrice(price);
		goodsOrder.setPayPrice(price);
		goodsOrder.setFreight(0d);
		goodsOrder.setCode(code);
		goodsOrder.setRemark(remark);
		goodsOrder.setPayType(1);
		goodsOrder.setStatus(1);
		goodsOrderDAO.create(goodsOrder);
		
		
		/**
		 * 创建订单清单
		 */
		goodsOrderDAO.createOrderList(idWorker.nextId(), orderId, goodsSkuId
			, userId, 1, price);
		
		return orderId;
	}

	@Override
	public Map getOrderPayMap(Long orderId) {
		return goodsOrderDAO.getOrderPayMap(orderId);
	}

	@Override
	public Boolean checkOrderPay(Long orderId) {
		GoodsOrder order = goodsOrderDAO.getById(orderId);
		if(order != null && order.getStatus().intValue() != 1){
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateOrderPay(Long orderId) {
		Integer result = goodsOrderDAO.updateOrderPay(orderId);
		if(result == null || result == 0){
			return false;
		}
		return true;
	}

	@Override
	public Boolean updateOrderCancel(Long orderId) {
		Integer result = goodsOrderDAO.updateOrderCancel(orderId);
		if(result == null || result == 0){
			return false;
		}
		return true;
	}

}
