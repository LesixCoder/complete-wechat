package com.lunchtasting.server.dao.user.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.user.UserDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.User;
import com.lunchtasting.server.util.ValidatorHelper;

@Repository
public class UserDAOImpl extends GenericDAOSupport<Long, User> implements UserDAO {

	@Override
	protected Class<?> getObjectClass() {
		return User.class;
	}

	@Override
	protected String getTableName() {
		return "user";
	}
	
	
	@Override
	public User getUserByPhone(String phone) {
		if(ValidatorHelper.isEmpty(phone)){
			return null;
		}
		
		Map map = new HashMap();
		map.put("phone", phone);
		return (User) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserByPhone", map);
	}

	@Override
	public List queryUserList(Integer page, Integer pageSize) {
		Map map = new HashMap();
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryUserList", map);
	}

	@Override
	public Integer updateUserResult(User user) {
		// TODO Auto-generated method stub
		 return getSqlMapClientTemplate().update(getNamespace() + ".updateUserResult", user);
	}

}
