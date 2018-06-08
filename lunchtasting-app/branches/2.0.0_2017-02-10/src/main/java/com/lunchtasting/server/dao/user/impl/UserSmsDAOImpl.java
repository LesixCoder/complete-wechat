package com.lunchtasting.server.dao.user.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.lunchtasting.server.dao.user.UserSmsDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.UserSms;
import com.lunchtasting.server.util.ValidatorHelper;

@Repository
public class UserSmsDAOImpl extends GenericDAOSupport<Long,UserSms> implements UserSmsDAO {

	@Override
	protected Class<?> getObjectClass() {
		return UserSms.class;
	}
	
	@Override
	protected String getTableName() {
		return "user_sms";
	}

	public UserSms getByPhoneAndType(String phone,Integer type) {
		if(ValidatorHelper.isEmpty(phone) || ValidatorHelper.isEmpty(type)){
			return null;
		}
		
		Map map = new HashMap();
		map.put("phone", phone);
		map.put("type", type);
		return (UserSms) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getByPhoneAndType", map);
	}

	@Override
	public Integer updateCodeExpire(Long id) {
		if(ValidatorHelper.isEmpty(id)){
			return 0;
		}
		Map map = new HashMap();
		map.put("id", id);
		return getSqlMapClientTemplate().update(getNamespace() + ".updateCodeExpire", map);
	}
}
