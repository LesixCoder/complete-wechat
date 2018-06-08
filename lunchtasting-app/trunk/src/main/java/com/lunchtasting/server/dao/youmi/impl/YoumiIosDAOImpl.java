package com.lunchtasting.server.dao.youmi.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.youmi.YoumiIosDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.YoumiIos;
@Repository
public class YoumiIosDAOImpl extends GenericDAOSupport<Long,YoumiIos> implements YoumiIosDAO{

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return YoumiIos.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "youmi_ios";
	}
	
	@Override
	public Integer updateYoumi(String url, Integer code,String idfa) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("url", url);
		map.put("code",code);
		map.put("idfa", idfa);
		return (Integer) getSqlMapClientTemplate().update(getNamespace() + ".updateYoumi", map);
	}

	@Override
	public Map getYoumiByIdfa(String idfa) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("idfa", idfa);
		return (Map) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getYoumiByIdfa", map);
	}

	@Override
	public Integer createYoumiIos(String idfa, String url) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("idfa", idfa);
		map.put("url", url);
		return (Integer) getSqlMapClientTemplate().insert(getNamespace() + ".createYoumiIos", map);
	}


}
