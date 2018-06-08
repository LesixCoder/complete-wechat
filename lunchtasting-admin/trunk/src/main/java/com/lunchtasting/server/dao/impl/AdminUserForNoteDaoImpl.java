package com.lunchtasting.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.AdminUserForNoteDao;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.User;
@Repository
public class AdminUserForNoteDaoImpl extends GenericDAOSupport<Long,User> implements AdminUserForNoteDao{
	
	@Override
	public List queryUserList() {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryUserList");
	}

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return User.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "user";
	}

	@Override
	public List queryUser1() {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryUser1");
	}

	@Override
	public List queryUser2() {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryUser2");
	}

	@Override
	public int deleteUser(String id) {
		HashMap map = new HashMap();
		map.put("id", id);
		return getSqlMapClientTemplate().delete(getNamespace() + ".updateUser",map);
	}

	@Override
	public Long saveUser(User user) {
		// TODO Auto-generated method stub
		return (Long) getSqlMapClientTemplate().insert(getNamespace() + ".create",user);
	}

	@Override
	public Long updateUser(User user) {
		// TODO Auto-generated method stub
		return (long) getSqlMapClientTemplate().update(getNamespace() + ".update",user);
	}

	@Override
	public User queryUserById(String id) {
		HashMap map = new HashMap();
		map.put("id", id);
		return (User) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getById",map);
	}

	@Override
	public int queryUserCount(HashMap map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryUserCount",map);
	}

	@Override
	public List queryUserList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryMarkUser",map);
	}

	@Override
	public int queryUserByPhone(String phone) {
		HashMap map = new HashMap();
		map.put("phone", phone);
		return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryUserByPhone",map);
	}

	@Override
	public Long saveDevice(Long id, Long userId) {
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("userId", userId);
		return (Long) getSqlMapClientTemplate().insert(getNamespace() + ".insertDevice",map);
	}

	@Override
	public Map getUserByPhone(String tel) {
		HashMap map = new HashMap();
		map.put("phone", tel);
		return (Map) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserByPhone",map);
	}

}
