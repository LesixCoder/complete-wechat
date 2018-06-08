package com.lunchtasting.server.biz.area.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.area.AreaBIZ;
import com.lunchtasting.server.dao.area.AreaDAO;
import com.lunchtasting.server.po.lt.Area;
import com.lunchtasting.server.vo.DataModel;

@Service
public class AreaBIZImpl implements AreaBIZ {
	@Autowired
	private AreaDAO areaDAO;
	
	@Override
	public List<Area> queryAreaListNew() {
		// TODO Auto-generated method stub
		HashMap map =  new HashMap();
		return areaDAO.queryAreaListNew(map);
	}

	@Override
	public List<DataModel> queryEdificeList(HashMap map) {
		// TODO Auto-generated method stub
		return areaDAO.queryEdificeList(map);
	}
}
