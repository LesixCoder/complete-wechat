package com.lunchtasting.server.dao.questionnaire.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.questionnaire.QuestionnaireDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Questionnaire;
@Repository
public class QuestionnaireDAOImpl extends GenericDAOSupport<Long,Questionnaire>implements QuestionnaireDAO {

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return Questionnaire.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "questionnaire";
	}

	@Override
	public boolean createList(List list) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert(getNamespace() + ".createList", list);
		return true;
	}

}
