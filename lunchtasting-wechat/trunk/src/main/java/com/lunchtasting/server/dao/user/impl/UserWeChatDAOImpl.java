package com.lunchtasting.server.dao.user.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.user.UserWeChatDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.UserWeChat;
import com.lunchtasting.server.util.ValidatorHelper;

@Repository
public class UserWeChatDAOImpl extends GenericDAOSupport<Long,UserWeChat> implements UserWeChatDAO {

	@Override
	protected Class<?> getObjectClass() {
		return UserWeChat.class;
	}

	@Override
	protected String getTableName() {
		return "user_weChat";
	}

	@Override
	public UserWeChat getByOpenId(String openId) {
		if(ValidatorHelper.isEmpty(openId)){
			return null;
		}
		Map map = new HashMap();
		map.put("openId", openId);
		return (UserWeChat) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getByOpenId", map);
	}

	@Override
	public UserWeChat getByUserId(Long userId) {
		if(ValidatorHelper.isEmpty(userId)){
			return null;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		return (UserWeChat) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getByUserId", map);
	}
	
	@Override
	public UserWeChat getByUnionId(String unionId) {
		if(ValidatorHelper.isEmpty(unionId)){
			return null;
		}
		Map map = new HashMap();
		map.put("unionId", unionId);
		return (UserWeChat) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getByUnionId", map);
	}

	@Override
	public Integer updateWeChat(UserWeChat userWeChat) {
		if(userWeChat == null){
			return 0;
		}
		return getSqlMapClientTemplate().update(getNamespace() + ".updateWeChat", userWeChat);
	}

	@Override
	public String getOpenIdByUserId(Long userId) {
		if(ValidatorHelper.isEmpty(userId)){
			return null;
		}
		
		Map map = new HashMap();
		map.put("userId", userId);
		return (String) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getOpenIdByUserId", map);	
	}


}
