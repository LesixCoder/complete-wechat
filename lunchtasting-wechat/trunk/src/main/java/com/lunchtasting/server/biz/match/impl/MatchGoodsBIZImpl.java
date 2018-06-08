package com.lunchtasting.server.biz.match.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.match.MatchGoodsBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.match.MatchGoodsDAO;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class MatchGoodsBIZImpl implements MatchGoodsBIZ {
	
	@Autowired
	private MatchGoodsDAO matchGoodsDAO;
	
	
	@Override
	public List queryMatchTicketGoodsList(Long ticketId) {
		List list =  matchGoodsDAO.queryMatchTicketGoodsList(ticketId);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			double totalPrice = 0;
			for(int i =0 ;i<list.size();i++){
 				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 价格
				 */ 
				map.put("price", CommonHelper.getDobule(
							Double.parseDouble(map.get("price").toString())));
				
				
				/**
				 * 图片
				 */
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
						+ QiNiuStorageHelper.MODEL1+"w/300/h/300");
				
				newList.add(map);
			}
			return newList;
		}
		return null;
	}

	@Override
	public Map getUserSelectGoods(Long ticketId,String goodsStr) {
		
		String goodsIds = getGoodsIds(goodsStr);
		if(ValidatorHelper.isEmpty(goodsIds)){
			return null;
		}
		
		List goodsList = matchGoodsDAO.queryUserSelectGoodsList(ticketId, goodsIds);
  		if(ValidatorHelper.isNotEmpty(goodsList)){
			List newList = new ArrayList();
			double totalPrice = 0;
			for(int i =0 ;i<goodsList.size();i++){
 				HashMap map = (HashMap) goodsList.get(i);
				long matchGoodsId = Long.parseLong(map.get("match_goods_id").toString());
 				
				/**
				 * 判断获取用户选择的商品数量
				 */
				map.put("number", 1);
				String [] spiltA = goodsStr.split("\\|");
				if(ValidatorHelper.isNotEmpty(spiltA)){
					for(String strA : spiltA){
						String [] spiltB = strA.split("\\@");
						if(ValidatorHelper.isNumber(spiltB[0])
								&& ValidatorHelper.isNumber(spiltB[1])){
							long mgId = Long.parseLong(spiltB[0]);
							int number = Integer.parseInt(spiltB[1]);
							if(number < 0){
								number = 1;
							}
							if(number > 99){
								number = 99;
							}
							if(matchGoodsId == mgId){
								map.put("number", number);
							}
						}
					}
				}		
					
				/**
				 * 价格
				 */ 
				int number = Integer.parseInt(map.get("number").toString());
				double price = Double.parseDouble(map.get("price").toString());
				totalPrice = totalPrice + price*number;
	 			
	 			/**
	  			 * 图片
		 		 */
		 		map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
		 				+ QiNiuStorageHelper.MODEL1+"w/300/h/300");
		 		
		 		newList.add(map);
		 	}
		 	
		 	Map map = new HashMap();
		 	map.put("goodsList", newList);
		 	map.put("price", totalPrice);
		 	return map;
		} 
		 
		return null;
 	}
	
	private String getGoodsIds(String goodsStr){ 
		if(ValidatorHelper.isEmpty(goodsStr)){
			 return null;
		}
		
		StringBuffer sb = new StringBuffer();
		String [] spiltA = goodsStr.split("\\|");
		if(spiltA != null){
			for(String strA : spiltA){
				String [] spiltB = strA.split("\\@");
				if(ValidatorHelper.isNumber(spiltB[0])){
					sb.append(spiltB[0]).append(",");
				}
			}
			String str = sb.toString();
			if(ValidatorHelper.isNotEmpty(str)){
				if(str.substring(str.length()-1,str.length()).equals(",")){
					str = str.substring(0, str.length()-1);
				}
				return str;
			}
		}
		
		return null;
	}
 
	@Override 
	public Double getUserSelectGoodsPrice(Long ticketId,String goodsStr) {
		String goodsIds = getGoodsIds(goodsStr);
		if(ValidatorHelper.isEmpty(goodsIds)){
			return 0d;
		}
		
		List goodsList = matchGoodsDAO.queryUserSelectGoodsList(ticketId, goodsIds);
  		if(ValidatorHelper.isNotEmpty(goodsList)){
			double totalPrice = 0;
			for(int i =0 ;i<goodsList.size();i++){
 				HashMap map = (HashMap) goodsList.get(i);
				long matchGoodsId = Long.parseLong(map.get("match_goods_id").toString());
 				
				/**
				 * 判断获取用户选择的商品数量
				 */
				map.put("number", 1);
				String [] spiltA = goodsStr.split("\\|");
				if(ValidatorHelper.isNotEmpty(spiltA)){
					for(String strA : spiltA){
						String [] spiltB = strA.split("\\@");
						if(ValidatorHelper.isNumber(spiltB[0])
								&& ValidatorHelper.isNumber(spiltB[1])){
							long mgId = Long.parseLong(spiltB[0]);
							int number = Integer.parseInt(spiltB[1]);
							if(number < 0){
								number = 1;
							}
							if(number > 99){
								number = 99;
							}
							if(matchGoodsId == mgId){
								map.put("number", number);
							}
						}
					}
				}		
					
				/**
				 * 价格
				 */ 
				int number = Integer.parseInt(map.get("number").toString());
				double price = Double.parseDouble(map.get("price").toString());
				totalPrice = totalPrice + price*number;
		 	}
		 	
		 	return totalPrice;
		} 
		 
		return 0d;
	}

}
