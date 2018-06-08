package com.lunchtasting.server.biz.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lunchtasting.server.biz.AdminActivityEnrollBIZ;
import com.lunchtasting.server.dao.AdminActivityEnrollDao;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.po.lt.ActivityEnroll;
@Service
public class AdminActivityEnrollBIZImpl implements AdminActivityEnrollBIZ{
	@Autowired
	private AdminActivityEnrollDao adminActivityEnrollDao;
	@Override
	public HashMap queryEnroll(HashMap map) {
		HashMap result = new HashMap();
	    List<ActivityEnroll> activityEnrollList;
	    int totalCount;
    	 try{
    		 activityEnrollList = adminActivityEnrollDao.queryEnrollList(map);
    		 totalCount = adminActivityEnrollDao.queryEnrollListCount(map);
    	 }catch(Exception e){
			 e.printStackTrace();
			 result.put("result","1");
		     result.put("descript","查询失败");
		     result.put("totalCount",0);
		     result.put("page",new PageBean(null, Integer.parseInt(map.get("curPage").toString()), 0, Integer.parseInt(map.get("pageSize").toString())));
		     return result;
    	 }
    	 if(activityEnrollList != null && activityEnrollList.size() > 0){
    		 result.put("result","0");
		     result.put("descript","查询成功");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(activityEnrollList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }else{
    		 result.put("result","0");
		     result.put("descript","没有更多");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(activityEnrollList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }
    	 return result;
	}
}
