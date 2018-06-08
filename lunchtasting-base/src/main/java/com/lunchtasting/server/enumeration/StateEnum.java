package com.lunchtasting.server.enumeration;


/**
 * 枚举公共类
 * @author xuqian
 *
 */
public enum StateEnum {
	
	/**
	 * 客户端类型
	 */
	PLATFORM_ANDROID(1),PLATFORM_IOS(2),
	
	/**
	 * 短信类型
	 * 1注册短信,2密码找回,3活动报名短信
	 * 201 咆哮狗赛事报名
	 * 202 咆哮狗观察票报名
	 */
	SMS_REGISTER(1),SMS_GET_PASSWORD(2),SMS_ACTIVITY_ENROLL(3)
	,SMS_TEMPORARY_ACTIVITY(110)
	,SMS_PAXIAOGOU_MATCH_ENROLL(201)
	,SMS_PAXIAOGOU_MATCH_ENROLL_WATCH(202),
	
	/**
	 * 推送目标平台 
	 * 0.全部   1.android 2.ios
	 */
	PUSH_ALL(0),PUSH_ANDROID(1),PUSH_IOS(2),
	
	/**
	 * 用户身份
	 * 1.普通用户 ，2商家用户，3健身教练
	 */
	USER_IDENTITY_ORDINARY(1),
	//USER_IDENTITY_SALLER(2),USER_IDENTITY_TRAINER(3);
	
	/**
	 * 收藏
	 * 1活动 2文章 3场馆
	 */
	COLLECT_ACTIVITY(1),COLLECT_ARTICLE(2),COLLECT_SELLER(3),
	
	/**
	 * 1 支付宝 支付  2微信(财付通)支付
	 */
	Alipay(1),Tenpay(2),
	
	/**
	 * 订单支付状态
	 * 1待付款  2已付款  3待评价(已消费) 4已评价 5 已退款（已过期）
	 */
	ORDER_NOPAY(1),ORDER_ISPAY(2),ORDER_NOCOMMENT(3),ORDER_ISCOMMENT(4),ORDER_ISREFUND(5),
	
	/**
	 * 订单清单状态
	 * 1未使用  2已使用 3退款中 4已退款
	 */
	ORDER_LIST_NOT_USED(1),ORDER_LIST_IS_USED(2),ORDER_LIST_REFUNDING(3)
	,ORDER_LIST_IS_REFUND(4),
	
	/**
	 * 1课程支付  2活动支付
	 */
	PAY_COURSE(1),PAY_ACTIVITY(2);
	
	private int value;

	private StateEnum(int value){
		this.value = value;
	}
	
	public Integer getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
