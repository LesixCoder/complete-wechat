package com.lunchtasting.server.dao.temporaryEnroll.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.temporaryEnroll.TempooraryInvitationCodeDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.temporaryEnroll.TempooraryInvitationCode;
@Repository
public class TempooraryInvitationCodeDAOImpl extends GenericDAOSupport<Long,TempooraryInvitationCode>implements TempooraryInvitationCodeDAO{

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return TempooraryInvitationCode.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "tempoorary_invitation_code";
	}
	@Override
	public TempooraryInvitationCode getByCode(String code) {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		map.put("code", code);
		return (TempooraryInvitationCode) getSqlMapClientTemplate().queryForObject(getNamespace()+".getByCode",map);
	}

	@Override
	public boolean UpdateTempooraryInvitationCode(
			TempooraryInvitationCode invitationCode) {
		// TODO Auto-generated method stub
		int pf = getSqlMapClientTemplate().update(getNamespace()+".UpdateTempooraryInvitationCode",invitationCode);
		if(pf==1){
			return true;
		}
		return false;
	}
}
