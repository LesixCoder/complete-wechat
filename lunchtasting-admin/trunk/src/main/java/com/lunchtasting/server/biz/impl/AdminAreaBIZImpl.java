package com.lunchtasting.server.biz.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lunchtasting.server.biz.AdminAreaBIZ;
import com.lunchtasting.server.dao.AdminAreaDao;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.po.lt.Area;
import com.lunchtasting.server.po.lt.OrdersSettlement;
@Service
public class AdminAreaBIZImpl implements AdminAreaBIZ{
    @Autowired
    private AdminAreaDao adminAreaDao;
	@Override
	public List queryArea() {
		return adminAreaDao.queryArea();
	}
	@Override
	public Area queryAreaByName(String name) {
		// TODO Auto-generated method stub
		return adminAreaDao.queryAreaByName(name);
	}
	@Override
	public HashMap queryAreaList(HashMap map) {
		HashMap result = new HashMap();
	    List<Area> areaList;
	    int totalCount;
    	 try{
    		 areaList = adminAreaDao.queryAreaList(map);
    		 totalCount = adminAreaDao.queryAreaCount(map);
    	 }catch(Exception e){
			 e.printStackTrace();
			 result.put("result","1");
		     result.put("descript","查询失败");
		     result.put("totalCount",0);
		     result.put("page",new PageBean(null, Integer.parseInt(map.get("curPage").toString()), 0, Integer.parseInt(map.get("pageSize").toString())));
		     return result;
    	 }
    	 if(areaList != null && areaList.size() > 0){
    		 result.put("result","0");
		     result.put("descript","查询成功");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(areaList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }else{
    		 result.put("result","0");
		     result.put("descript","没有更多");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(areaList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }
		return result;
	}
	@Override
	public Long addArea(Area area) {
		// TODO Auto-generated method stub
		return adminAreaDao.addArea(area);
	}
	@Override
	public Long updateArea(Area area) {
		// TODO Auto-generated method stub
		return adminAreaDao.updateArea(area);
	}
	@Override
	public Long updateArea(HashMap map) {
		// TODO Auto-generated method stub
		return adminAreaDao.updateArea(map);
	}
	@Override
	public int queryNameCount(String name) {
		// TODO Auto-generated method stub
		return adminAreaDao.queryNameCount(name);
	}
	@Override
	public Area queryAreaById(String id) {
		// TODO Auto-generated method stub
		return adminAreaDao.queryAreaById(id);
	}

}
