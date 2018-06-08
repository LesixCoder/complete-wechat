package com.lunchtasting.server.dao.questionnaire;

import java.util.List;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.Questionnaire;

public interface QuestionnaireDAO  extends GenericDAO<Questionnaire>{
	boolean createList(List list); 
}
