package com.lunchtasting.server.enumeration;

public class SysEnum {

	/**
	 * 用户热度类型
	 */
	public enum UserHotType {
		ADD_NOTE("发布帖子", 1, 2), 
		ADD_NOTE_COMMENT("发布帖子评论", 2, 2), 
		LIKE_NOTE("喜欢帖子", 3, 2), 
		SHARE("转发至第三方", 4, 5), 
		FOLLOW_FRIEND("关注好友", 5,1), 
		ENROLL_MATCH("报名赛事", 6, 100), 
		BIND_PHONE("绑定手机", 7, 40);

		/**
		 * type类型 number分值
		 */
		private int type;
		private int number;

		private UserHotType(String description, int type, int number) {
			this.type = type;
			this.number = number;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public int getNumber() {
			return number;
		}

		public void setNumber(int number) {
			this.number = number;
		}
	}

	/**
	 * 系统通知消息
	 */
	public enum MessageNotificatioType {
		
		/**
		 * 100系统消息
		 * 200业务消息
		 */
		SYSTEM_NOTIFICATION("系统提示消息", 101, 2),
		NOTE_LIKE("帖子点赞", 201, 1), 
		NOTE_ADD_COMMENT("帖子评论", 202, 1), 
		FRIEND_FOLLOW("好友关注", 203, 1), 
		MATCH_BEGIN("赛事开始提醒消息", 204, 2), 
		ACTIVITY_BEGIN("活动开始提醒消息", 205, 2),
		MATCH_GROUP_INVITE("赛事组队邀请",206,2);


		/**
		 * type类型 msgType 1静默消息 2通知消息
		 */
		private int type;
		private int msgType;

		private MessageNotificatioType(String description, int type, int msgType) {
			this.type = type;
			this.msgType = msgType;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public int getMsgType() {
			return msgType;
		}

		public void setMsgType(int msgType) {
			this.msgType = msgType;
		}

		public static MessageNotificatioType getEnumByType(int typeValue) {
			MessageNotificatioType result = null;
			for (MessageNotificatioType type : values()) {
				if (type.getType() == typeValue) {
					result = type;
					break;
				}
			}
			return result;
		}
	}

	public static void main(String[] args) {
		System.out.println(SysEnum.UserHotType.ADD_NOTE.getNumber() + "//"
				+ SysEnum.UserHotType.ADD_NOTE.getType());
		
		System.out.println(MessageNotificatioType.getEnumByType(SysEnum.MessageNotificatioType.NOTE_ADD_COMMENT.getMsgType()).getMsgType());
	}
}
