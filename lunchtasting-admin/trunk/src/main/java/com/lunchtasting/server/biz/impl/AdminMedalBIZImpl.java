package com.lunchtasting.server.biz.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lunchtasting.server.biz.AdminMedalBIZ;
import com.lunchtasting.server.dao.AdminMedalDao;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.po.lt.Medal;

@Service
public class AdminMedalBIZImpl implements AdminMedalBIZ{
	
	@Autowired
	private AdminMedalDao adminMedalDao;
		
	@Override
	public List queryMedalNotLimit() {
		// TODO Auto-generated method stub
		return adminMedalDao.queryMedalNotLimit();
	}

	@Override
	public Long saveMedal(Medal medal) {
		// TODO Auto-generated method stub
		return adminMedalDao.saveMedal(medal);
	}

	@Override
	public Long updateMedal(Medal medal) {
		// TODO Auto-generated method stub
		return adminMedalDao.updateMedal(medal);
	}

	@Override
	public HashMap queryMedalList(HashMap map) {
		HashMap result = new HashMap();
	    List<Medal> medalList;
	    int totalCount;
    	 try{
    		 medalList = adminMedalDao.queryMedalList(map);
    		 for(int i = 0;i<medalList.size();i++){
    			 medalList.get(i).setNewId(medalList.get(i).getId().toString());
    		 }
    		 totalCount = adminMedalDao.queryMedalCount(map);
    	 }catch(Exception e){
			 e.printStackTrace();
			 result.put("result","1");
		     result.put("descript","查询失败");
		     result.put("totalCount",0);
		     result.put("page",new PageBean(null, Integer.parseInt(map.get("curPage").toString()), 0, Integer.parseInt(map.get("pageSize").toString())));
		     return result;
    	 }
    	 if(medalList != null && medalList.size() > 0){
    		 result.put("result","0");
		     result.put("descript","查询成功");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(medalList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }else{
    		 result.put("result","0");
		     result.put("descript","没有更多");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(medalList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }
		return result;
	}

	@Override
	public Medal queryMedalById(String id) {
		// TODO Auto-generated method stub
		return adminMedalDao.queryMedalById(id);
	}

	@Override
	public int queryMedalCount(HashMap map) {
		// TODO Auto-generated method stub
		return adminMedalDao.queryMedalCount(map);
	}

	@Override
	public int deleteMedal(String id) {
		// TODO Auto-generated method stub
		return adminMedalDao.deleteMedal(id);
	}

	@Override
	public int queryMedalByName(String name) {
		// TODO Auto-generated method stub
		return adminMedalDao.queryMedalByName(name);
	}

}
