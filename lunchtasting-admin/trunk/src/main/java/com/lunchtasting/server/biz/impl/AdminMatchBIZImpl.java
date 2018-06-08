package com.lunchtasting.server.biz.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lunchtasting.server.biz.AdminMatchBIZ;
import com.lunchtasting.server.dao.AdminMatchDao;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.po.lt.Match;

@Service
public class AdminMatchBIZImpl implements AdminMatchBIZ{

	@Autowired
	private AdminMatchDao adminMatchDao;
	
	@Override
	public Long saveMatch(Match match) {
		// TODO Auto-generated method stub
		return adminMatchDao.saveMatch(match);
	}

	@Override
	public Long updateMatch(Match match) {
		// TODO Auto-generated method stub
		return adminMatchDao.updateMatch(match);
	}

	@Override
	public HashMap queryMatchList(HashMap map) {
		HashMap result = new HashMap();
	    List<Match> matchList;
	    int totalCount;
    	 try{
    		 matchList = adminMatchDao.queryMatchList(map);
    		 for(int i = 0;i<matchList.size();i++){
    			 matchList.get(i).setNewId(matchList.get(i).getId().toString());
    		 }
    		 totalCount = adminMatchDao.queryMatchCount(map);
    	 }catch(Exception e){
			 e.printStackTrace();
			 result.put("result","1");
		     result.put("descript","查询失败");
		     result.put("totalCount",0);
		     result.put("page",new PageBean(null, Integer.parseInt(map.get("curPage").toString()), 0, Integer.parseInt(map.get("pageSize").toString())));
		     return result;
    	 }
    	 if(matchList != null && matchList.size() > 0){
    		 result.put("result","0");
		     result.put("descript","查询成功");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(matchList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }else{
    		 result.put("result","0");
		     result.put("descript","没有更多");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(matchList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }
		return result;
	}

	@Override
	public Match queryMatchById(String id) {
		// TODO Auto-generated method stub
		return adminMatchDao.queryMatchById(id);
	}

	@Override
	public int queryMatchCount(HashMap map) {
		// TODO Auto-generated method stub
		return adminMatchDao.queryMatchCount(map);
	}

	@Override
	public int deleteMatch(HashMap map) {
		// TODO Auto-generated method stub
		return adminMatchDao.deleteMatch(map);
	}

	@Override
	public int topMatch(HashMap map) {
		// TODO Auto-generated method stub
		return adminMatchDao.topMatch(map);
	}

	@Override
	public List queryMatchNotLimit() {
		// TODO Auto-generated method stub
		return adminMatchDao.queryMatchNotLimit();
	}

}
