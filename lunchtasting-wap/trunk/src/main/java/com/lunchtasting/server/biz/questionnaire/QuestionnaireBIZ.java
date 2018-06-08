package com.lunchtasting.server.biz.questionnaire;

public interface QuestionnaireBIZ {
	/**
	 * 插入问卷数据
	 * @return
	 */
	boolean entering(String listStr);
	
	/**
	 * 
	 * @return
	 */
	boolean enteringOne(Integer topicId,String topicTitle,String answerType,Integer type,Long userId,String userInput);
}
