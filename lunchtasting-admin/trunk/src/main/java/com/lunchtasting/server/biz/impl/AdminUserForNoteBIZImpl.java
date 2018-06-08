package com.lunchtasting.server.biz.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lunchtasting.server.biz.AdminUserForNoteBIZ;
import com.lunchtasting.server.dao.AdminUserForNoteDao;
import com.lunchtasting.server.helper.PageBean;
import com.lunchtasting.server.po.lt.Note;
import com.lunchtasting.server.po.lt.User;
@Service
public class AdminUserForNoteBIZImpl implements AdminUserForNoteBIZ{
	
	@Autowired
	private AdminUserForNoteDao adminUserForNoteDao;
	
	@Override
	public List queryUserList() {
		// TODO Auto-generated method stub
		return adminUserForNoteDao.queryUserList();
	}

	@Override
	public List queryUser1() {
		// TODO Auto-generated method stub
		return adminUserForNoteDao.queryUser1();
	}

	@Override
	public List queryUser2() {
		// TODO Auto-generated method stub
		return adminUserForNoteDao.queryUser2();
	}

	@Override
	public int deleteUser(String id) {
		// TODO Auto-generated method stub
		return adminUserForNoteDao.deleteUser(id);
	}

	@Override
	public Long saveUser(User user) {
		// TODO Auto-generated method stub
		return adminUserForNoteDao.saveUser(user);
	}

	@Override
	public Long updateUser(User user) {
		// TODO Auto-generated method stub
		return adminUserForNoteDao.updateUser(user);
	}

	@Override
	public User queryUserById(String id) {
		// TODO Auto-generated method stub
		return adminUserForNoteDao.queryUserById(id);
	}

	@Override
	public HashMap queryUserList(HashMap map) {
		HashMap result = new HashMap();
	    List<User> userList;
	    int totalCount;
    	 try{
    		 userList = adminUserForNoteDao.queryUserList(map);
    		 for(int i = 0;i<userList.size();i++){
    			 userList.get(i).setNewId(userList.get(i).getId().toString());
    		 }
    		 totalCount = adminUserForNoteDao.queryUserCount(map);
    	 }catch(Exception e){
			 e.printStackTrace();
			 result.put("result","1");
		     result.put("descript","查询失败");
		     result.put("totalCount",0);
		     result.put("page",new PageBean(null, Integer.parseInt(map.get("curPage").toString()), 0, Integer.parseInt(map.get("pageSize").toString())));
		     return result;
    	 }
    	 if(userList != null && userList.size() > 0){
    		 result.put("result","0");
		     result.put("descript","查询成功");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(userList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }else{
    		 result.put("result","0");
		     result.put("descript","没有更多");
		     result.put("totalCount",totalCount);
		     result.put("page",new PageBean(userList, Integer.parseInt(map.get("curPage").toString()), totalCount, Integer.parseInt(map.get("pageSize").toString())));
    	 }
    	 return result;
	}

	@Override
	public int queryUserByPhone(String phone) {
		// TODO Auto-generated method stub
		return adminUserForNoteDao.queryUserByPhone(phone);
	}

	@Override
	public Long saveDevice(Long id, Long userId) {
		// TODO Auto-generated method stub
		return adminUserForNoteDao.saveDevice(id, userId);
	}

}
