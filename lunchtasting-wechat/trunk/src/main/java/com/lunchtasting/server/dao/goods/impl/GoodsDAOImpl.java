package com.lunchtasting.server.dao.goods.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.goods.GoodsDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.goods.Goods;
import com.lunchtasting.server.util.ValidatorHelper;

@Repository
public class GoodsDAOImpl extends GenericDAOSupport<Long, Goods> implements GoodsDAO {

	@Override
	protected Class<?> getObjectClass() {
		return Goods.class;
	}

	@Override
	protected String getTableName() {
		return "goods";
	}
	
	@Override
	public List queryGoodsList(Integer page, Integer pageSize) {
		Map map = new HashMap();
		map.put("page", page);
		map.put("pageSize", pageSize);
        return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryGoodsList", map);
    }

	@Override
	public Integer getGoodsCount() {
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getGoodsCount");	
	}

	@Override
	public Map getGoodsDetail(Long goodsId) {
		if(goodsId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("goodsId", goodsId);
        return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getGoodsDetail", map);	
	}
	
	@Override
	public List queryGoodsSkuPropertyList(Long goodsId) {
		if(ValidatorHelper.isEmpty(goodsId)){
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goodsId", goodsId);
        return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryGoodsSkuPropertyList", map);
	}

	@Override
	public String getGoodsSkuPropertStr(Long goodsId) {
		if(ValidatorHelper.isEmpty(goodsId)){
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goodsId", goodsId);
        return (String)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getGoodsSkuPropertStr", map);
	}

	@Override
	public List queryGoodsSkuList(Long goodsId) {
		if(ValidatorHelper.isEmpty(goodsId)){
			return null;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goodsId", goodsId);
        return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryGoodsSkuList", map);
	}

	@Override
	public Map getGoodsPayMap(Long goodsSkuId) {
		if(goodsSkuId == null){
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goodsSkuId", goodsSkuId);
        return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getGoodsPayMap", map);
	}

	@Override
	public List queryGoodsPropertyList(Long goodsId) {
		if(goodsId == null){
			return null;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goodsId", goodsId);
        return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryGoodsPropertyList", map);
	}


	@Override
	public List getGoodsSkuByOptionIds(String optionIds) {
		if(ValidatorHelper.isEmpty(optionIds)){
			return null;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("optionIds", optionIds);
        return getSqlMapClientTemplate().queryForList(getNamespace() + ".getGoodsSkuByOptionIds", map);
    }


}
