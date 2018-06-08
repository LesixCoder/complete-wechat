package com.lunchtasting.server.dao.temporaryEnroll;

import java.util.HashMap;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.temporaryEnroll.TempooraryInvitationCode;

public interface TempooraryInvitationCodeDAO extends GenericDAO<TempooraryInvitationCode>{
	/**
	 * 得带code信息ByCode
	 * @param map
	 * @return
	 */
	TempooraryInvitationCode getByCode(String code);
	/**
	 * 更新code（使用）
	 * @param invitationCode
	 * @return
	 */
	boolean UpdateTempooraryInvitationCode(TempooraryInvitationCode invitationCode);
}
