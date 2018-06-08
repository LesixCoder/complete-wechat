package com.lunchtasting.server.dao.area.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.area.AreaDAO;
import com.lunchtasting.server.dao.user.UsersInfoDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Area;
import com.lunchtasting.server.po.lt.UsersInfo;
import com.lunchtasting.server.util.ValidatorHelper;
import com.lunchtasting.server.vo.DataModel;

@Repository
public class AreaDAOImpl extends GenericDAOSupport<Long, Area> implements AreaDAO {

	@Override
	protected Class<?> getObjectClass() {
		return Area.class;
	}

	@Override
	protected String getTableName() {
		return "area";
	}

	@Override
	public List<Area> queryAreaList(HashMap map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int queryAreaListCount(HashMap map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addArea(Area area) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateArea(Area area) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteArea(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Area> queryAreaListNew(HashMap map) {
		if(map == null){
			return null;
		}
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryAreaListNew", map);
	}

	@Override
	public List<DataModel> queryEdificeList(HashMap map) {
		if(map == null){
			return null;
		}
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryEdificeList", map);
	}
}
