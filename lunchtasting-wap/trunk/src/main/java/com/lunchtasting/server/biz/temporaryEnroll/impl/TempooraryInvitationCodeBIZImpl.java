package com.lunchtasting.server.biz.temporaryEnroll.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.temporaryEnroll.TempooraryInvitationCodeBIZ;
import com.lunchtasting.server.dao.temporaryEnroll.TempooraryInvitationCodeDAO;
import com.lunchtasting.server.po.temporaryEnroll.TempooraryInvitationCode;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.Utils;

@Service
public class TempooraryInvitationCodeBIZImpl implements TempooraryInvitationCodeBIZ{
	@Autowired
	private TempooraryInvitationCodeDAO codeDAO; 
	@Override
	public boolean verdictCode(String code) {
		// TODO Auto-generated method stub
		TempooraryInvitationCode invitationCode = codeDAO.getByCode(code);
		if(invitationCode==null){
			return false;
		}
		if(invitationCode.getState()==0 && invitationCode.getUserId()==null){
			return true;
		}
		return false;
	}

	@Override
	public boolean employCode(String code, Long userId) {
		// TODO Auto-generated method stub
		if(!verdictCode(code)){
			return false;
		}
		TempooraryInvitationCode invitationCode = new TempooraryInvitationCode();
		invitationCode.setCode(code);
		invitationCode.setUserId(userId);
		invitationCode.setState(1);
		return codeDAO.UpdateTempooraryInvitationCode(invitationCode);
	}

	@Override
	public boolean add(int con) {
		// TODO Auto-generated method stub
		for (int i = 0; i < con; i++) {
			String r=null;
			boolean pf = true; 
			while (pf) {
				r = Utils.getRandomNumber(6);
				TempooraryInvitationCode invitationCode = codeDAO.getByCode(r);
				if(invitationCode==null){
					pf=false;
				}
			}
			TempooraryInvitationCode invitationCode = new TempooraryInvitationCode();
			invitationCode.setState(0);
			invitationCode.setCode(r);
			invitationCode.setId(IdWorker.getId());
			codeDAO.create(invitationCode);
		}
		return true;
	}

}
