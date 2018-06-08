package com.lunchtasting.server.biz.seller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.seller.AreaBIZ;
import com.lunchtasting.server.dao.seller.AreaDAO;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class AreaBIZImpl implements AreaBIZ {
	
	@Autowired
	private AreaDAO areaDAO;
	
	@Override
	public List queryAreaList() {
		List list = areaDAO.queryAreaList();
		if(ValidatorHelper.isNotEmpty(list)){
			Map map = new HashMap();
			map.put("area_id", 0);
			map.put("name", "附近");
			list.add(0, map);
		}
		return list;
	}

}
