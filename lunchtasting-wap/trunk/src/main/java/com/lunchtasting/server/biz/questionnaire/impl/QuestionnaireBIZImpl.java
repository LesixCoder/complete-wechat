package com.lunchtasting.server.biz.questionnaire.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lunchtasting.server.biz.questionnaire.QuestionnaireBIZ;
import com.lunchtasting.server.dao.questionnaire.QuestionnaireDAO;
import com.lunchtasting.server.po.lt.Questionnaire;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;
@Service
public class QuestionnaireBIZImpl implements QuestionnaireBIZ{
	@Autowired
	private QuestionnaireDAO questionnaireDAO;
	@Override
	public boolean entering(String listStr) {
		// TODO Auto-generated method stub
		Long userId = IdWorker.getId();
		List listBean = new ArrayList();
		Map map = JSONObject.parseObject(listStr);
		List list = (List)map.get("alldata");
		
		Questionnaire tel = new Questionnaire();
		tel.setTopicId(1001);
		tel.setTopicTitle("联系方式");
		tel.setUserInput((String)map.get("issueCat"));
		tel.setUserId(userId);
		listBean.add(tel);
		
		Questionnaire job = new Questionnaire();
		job.setTopicId(1002);
		job.setTopicTitle("职业");
		job.setUserInput((String)map.get("issueJob"));
		job.setUserId(userId);
		listBean.add(job);
		
		for (int i = 0; i < list.size(); i++) {
			Map beanMap = (Map)list.get(i);
			Questionnaire questionnaire = new Questionnaire();
			System.out.println(i);
			questionnaire.setTopicId(Integer.parseInt(beanMap.get("issueNum").toString()));
			
			questionnaire.setAnswerType((String)beanMap.get("issueChecked"));
			
			questionnaire.setTopicTitle((String)beanMap.get("issueDes"));
			Integer type = 1;
			if(ValidatorHelper.isNumber((String)beanMap.get("type"))){
				type=Integer.parseInt((String)beanMap.get("type"));
			}
			questionnaire.setType(type);
			
			questionnaire.setUserId(userId);
			
			questionnaire.setUserInput((String)beanMap.get("userInput"));
			
			listBean.add(questionnaire);
		}
		questionnaireDAO.createList(listBean);
		return true;
	}

	@Override
	public boolean enteringOne(Integer topicId, String topicTitle,
			String answerType, Integer type, Long userId, String userInput) {
		// TODO Auto-generated method stub
		Questionnaire questionnaire = new Questionnaire();
		questionnaire.setTopicId(topicId);
		questionnaire.setAnswerType(answerType);
		questionnaire.setTopicTitle(topicTitle);
		questionnaire.setType(type);
		questionnaire.setUserId(userId);
		questionnaire.setUserInput(userInput);
		questionnaireDAO.create(questionnaire);
		return true;
	}
	public Questionnaire getBean(Integer topicId, String topicTitle,
			String answerType, Integer type, Long userId, String userInput) {
		// TODO Auto-generated method stub
		Questionnaire questionnaire = new Questionnaire();
		questionnaire.setTopicId(topicId);
		questionnaire.setAnswerType(answerType);
		questionnaire.setTopicTitle(topicTitle);
		questionnaire.setType(type);
		questionnaire.setUserId(userId);
		questionnaire.setUserInput(userInput);
		return questionnaire;
	}
	public static void main(String[] args) {
	}
}
