package com.lunchtasting.server.biz.timer;

public interface TimerCourseBIZ {

	/**
	 * 定时任务批量修改
	 * 修改当前时间大于开课时间的订单状态为已开课（进行中）
	 */
	void courseUp();
	
	/**
	 * 定时任务批量修改
	 * 修改当前时间大于结课时间的订单状态为已结束
	 */
	void courseFinish();
	
	/**
	 * 课程提现分红计算
	 * @throws Exception
	 */
	void courseBonus() throws Exception;
	
	/**
	 * 课程上课通知短信
	 */
	void courseUpNotifiyMeessage();
	
//	/**
//	 * 课程满团退还部分费用给用户
//	 * @throws Exception
//	 */
//	void courseFullRefund() throws Exception;
}
