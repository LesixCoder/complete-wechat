package com.lunchtasting.server.biz.goods.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.goods.GoodsBIZ;
import com.lunchtasting.server.dao.goods.GoodsDAO;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.po.lt.goods.GoodsOrder;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class GoodsBIZImpl implements GoodsBIZ {

	@Autowired
	private GoodsDAO goodsDAO;
	
	@Override
	public List queryGoodsList(Integer page,Integer pageSize) {
		return goodsDAO.queryGoodsList(page, pageSize);
	}

	@Override
	public Integer getGoodsCount() {
		return goodsDAO.getGoodsCount();
	}


	@Override
	public Map getGoodsDetail(Long goodsId) {
		return goodsDAO.getGoodsDetail(goodsId);
	}

	@Override
	public List queryGoodsSkuPropertyList(Long goodsId) {
		List list = goodsDAO.queryGoodsSkuPropertyList(goodsId);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 组装属性列表
				 */
				List optionList = null;
				if(ValidatorHelper.isMapNotEmpty(map.get("property_str"))){
					optionList = new ArrayList();
					String propertyStr =  map.get("property_str").toString();
					String [] aSpilt = propertyStr.split("\\|");
					if(ValidatorHelper.isNotEmpty(aSpilt)){
						Map optionMap = null;
						for(String s : aSpilt){
							String [] bSpilt = s.split("\\@");
							if(ValidatorHelper.isNotEmpty(bSpilt)){
								optionMap = new HashMap();
								optionMap.put("option_id", bSpilt[0]);
								optionMap.put("option_name", bSpilt[1]);
								optionMap.put("price", bSpilt[2]);
								 //optionMap.put("img_url", VariableHelper.IMAGE_VISIT_URL+bSpilt[3]);
								optionList.add(optionMap);
							}
						}
					}
				}
				map.remove("property_str");
				map.put("option_list", optionList);
				newList.add(map);
			}
			return newList;
		}
		
		return null;	
	}

	@Override
	public String getGoodsSkuPropertStr(Long goodsId) {
		return goodsDAO.getGoodsSkuPropertStr(goodsId);
	}

	@Override
	public List queryGoodsSkuList(Long goodsId) {
		List list = goodsDAO.queryGoodsSkuList(goodsId);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 图片
				 */
				map.put("img_url", VariableHelper.IMAGE_VISIT_URL+map.get("img_url"));

				/**
				 * 可选项id串
				 */
				String optionIds = map.get("sku_option_ids").toString();
				optionIds = optionIds.replaceAll(",", "-");
				map.put("str_ids", optionIds);
				newList.add(map);
			}
			return newList;
		}
		return null;
	}

	@Override
	public Map getGoodsPayMap(Long goodsSkuId) {
		return goodsDAO.getGoodsPayMap(goodsSkuId);
	}

	@Override
	public List queryGoodsPropertyList(Long goodsId) {
		return goodsDAO.queryGoodsPropertyList(goodsId);
	}

	@Override
	public Map getGoodsSkuByOptionIds(String optionIds) {
		List list = goodsDAO.getGoodsSkuByOptionIds(optionIds);
		if(ValidatorHelper.isNotEmpty(list)){
			int num1 = optionIds.split(",").length;
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 判断选择属性的数量和数据库sku属性的数量是否一致
				 */
				String optionStr = map.get("option_str").toString();
				if(ValidatorHelper.isNotEmpty(optionStr)){
					int num2 = optionStr.split(",").length;
					if(num1 == num2){
						return map;
					}
				}
			}
						
		}
		
		return null;
	}

}
