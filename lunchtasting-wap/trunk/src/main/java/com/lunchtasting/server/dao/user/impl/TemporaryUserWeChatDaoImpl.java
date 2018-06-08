package com.lunchtasting.server.dao.user.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.user.TemporaryUserWeChatDao;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.TemporaryUserWeChat;
@Repository
public class TemporaryUserWeChatDaoImpl extends GenericDAOSupport<Long,TemporaryUserWeChat> implements TemporaryUserWeChatDao{

	@Override
	public Long addTemp(TemporaryUserWeChat temporaryUserWeChat) {
		// TODO Auto-generated method stub
		return (Long) getSqlMapClientTemplate().insert(getNamespace() + ".create",temporaryUserWeChat);
	}

	@Override
	public Long updateTemp(TemporaryUserWeChat temporaryUserWeChat) {
		// TODO Auto-generated method stub
		return (long) getSqlMapClientTemplate().update(getNamespace() + ".updateTemporaryUserWeChat", temporaryUserWeChat);
	}

	@Override
	public TemporaryUserWeChat getByOpenId(String openId) {
		HashMap map = new HashMap();
		map.put("openId", openId);
		return (TemporaryUserWeChat) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getByOpenId",map);
	}

	@Override
	public TemporaryUserWeChat getByUserId(Long userId) {
		HashMap map = new HashMap();
		map.put("id", userId);
		return (TemporaryUserWeChat) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getByUserId",map);
	}

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return TemporaryUserWeChat.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "temporary_user_weChat";
	}

	@Override
	public boolean updateOutTradeNoById(String outTradeNo, Long userId) {
		// TODO Auto-generated method stub
		HashMap map =  new HashMap();
		map.put("outTradeNo",outTradeNo);
		map.put("id",userId);
		int pf = getSqlMapClientTemplate().update(getNamespace() + ".updateOutTradeNoById",map);
		if(pf==1){
			return true;
		}
		return false;
	}

}
