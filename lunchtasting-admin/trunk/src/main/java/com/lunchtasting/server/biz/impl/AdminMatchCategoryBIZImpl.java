package com.lunchtasting.server.biz.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.AdminMatchCategoryBIZ;
import com.lunchtasting.server.dao.AdminMatchCategoryDao;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.po.lt.Match;
import com.lunchtasting.server.po.lt.MatchCategory;
@Service
public class AdminMatchCategoryBIZImpl implements AdminMatchCategoryBIZ{
	
	@Autowired
	private AdminMatchCategoryDao adminMatchCategoryDao;
	
	@Override
	public List queryMatchCategoryNotLimit() {
		// TODO Auto-generated method stub
		return adminMatchCategoryDao.queryMatchCategoryNotLimit();
	}

	@Override
	public Long saveMatchCategory(MatchCategory matchCategory) {
		// TODO Auto-generated method stub
		return adminMatchCategoryDao.saveMatchCategory(matchCategory);
	}

	@Override
	public Long updateMatchCategory(MatchCategory matchCategory) {
		// TODO Auto-generated method stub
		return adminMatchCategoryDao.updateMatchCategory(matchCategory);
	}

	@Override
	public HashMap queryMatchCategoryList(HashMap map) {
		HashMap result = new HashMap();
	    List<MatchCategory> matchCategoryList;
	    int totalCount;
    	 try{
    		 matchCategoryList = adminMatchCategoryDao.queryMatchCategoryList(map);
    		 for(int i = 0;i<matchCategoryList.size();i++){
    			 matchCategoryList.get(i).setNewId(matchCategoryList.get(i).getId().toString());
    		 }
    		 totalCount = adminMatchCategoryDao.queryMatchCategoryCount(map);
    	 }catch(Exception e){
			 e.printStackTrace();
			 result.put("result","1");
		     result.put("descript","查询失败");
		     result.put("totalCount",0);
		     result.put("page",new PageBean(null, Integer.parseInt(map.get("curPage").toString()), 0, Integer.parseInt(map.get("pageSize").toString())));
		     return result;
    	 }
    	 if(matchCategoryList != null && matchCategoryList.size() > 0){
    		 result.put("result","0");
		     result.put("descript","查询成功");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(matchCategoryList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }else{
    		 result.put("result","0");
		     result.put("descript","没有更多");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(matchCategoryList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }
		return result;
	}

	@Override
	public MatchCategory queryMatchCategoryById(String id) {
		// TODO Auto-generated method stub
		return adminMatchCategoryDao.queryMatchCategoryById(id);
	}

	@Override
	public int queryMatchCategoryCount(HashMap map) {
		// TODO Auto-generated method stub
		return adminMatchCategoryDao.queryMatchCategoryCount(map);
	}

	@Override
	public int deleteMatchCategory(HashMap map) {
		// TODO Auto-generated method stub
		return adminMatchCategoryDao.deleteMatchCategory(map);
	}

	@Override
	public int queryMatchCategoryByName(String name) {
		// TODO Auto-generated method stub
		return adminMatchCategoryDao.getMatchCategoryByName(name);
	}

}
