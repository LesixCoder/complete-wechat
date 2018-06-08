package com.lunchtasting.server.biz.match.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lunchtasting.server.biz.match.MatchOrderBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.match.MatchGoodsDAO;
import com.lunchtasting.server.dao.match.MatchGroupDAO;
import com.lunchtasting.server.dao.match.MatchOrderDAO;
import com.lunchtasting.server.dao.match.MatchTicketDAO;
import com.lunchtasting.server.dao.user.UserDAO;
import com.lunchtasting.server.dao.user.UserSmsDAO;
import com.lunchtasting.server.dao.user.UserWeChatDAO;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.helper.WeChatMessageTemplet;
import com.lunchtasting.server.po.lt.Match;
import com.lunchtasting.server.po.lt.MatchGroup;
import com.lunchtasting.server.po.lt.MatchOrder;
import com.lunchtasting.server.po.lt.UserWeChat;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.DateUtil;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;

@Transactional(rollbackFor = Throwable.class)
@Service
public class MatchOrderBIZImpl implements MatchOrderBIZ{
	
	@Autowired
	private MatchOrderDAO matchOrderDAO;
	@Autowired 
	private MatchGroupDAO matchGroupDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserWeChatDAO userWeChatDAO;
	@Autowired
	private MatchTicketDAO matchTicketDAO;
	@Autowired
	private MatchGoodsDAO matchGoodsDAO;
	
	
	@Override
	public Long createMatchOrder(Long userId,Long matchId,Long ticketId,Double price,Double ticketPrice,Double goodsPrice,
			String signupStr,String goodsStr) throws Exception {
		
		IdWorker idWorker = new IdWorker(0, 0);
		/**
		 * 创建报名
		 */
		Map signupMap = new HashMap();
		long signupId = idWorker.nextId();
		signupMap.put("id", signupId);
		signupMap.put("ticketId", ticketId);
		signupMap.put("userId", userId);
		signupMap.put("matchId", matchId);
		Integer r1 = matchTicketDAO.createMatchTicketSignup(signupMap);
//		if(r1 == null || r1 == 0){
//			return null;
//		}
		
		/**
		 * 创建报名信息
		 */
		String [] splitA = signupStr.split("\\|");
		if(splitA != null){
			for(String strA : splitA){
				String [] splitB = strA.split("\\@");
				if(splitB != null){
					
					int i = 1;
					Map signupInfoMap = new HashMap();
					for(String strB : splitB){
						signupInfoMap.put("field"+i, strB);
						i = i +1;
					}

					signupInfoMap.put("id", idWorker.nextId());
					signupInfoMap.put("signupId", signupId);
					Integer r2 = matchTicketDAO.createMatchTicketSignupInfo(signupInfoMap);
//					if(r2 == null || r2 == 0){
//						throw new Exception();
//					}
				}
			}
		}
		
		/**
		 * 创建订单信息
		 */
		Map orderMap = new HashMap();
		long orderId = idWorker.nextId();
		orderMap.put("id", orderId);
		orderMap.put("matchId", matchId);
		orderMap.put("userId", userId);
		orderMap.put("signupId", signupId);
		orderMap.put("ticketId", ticketId);
		orderMap.put("price", price);
		orderMap.put("payPrice", price);
		orderMap.put("ticketPrice", ticketPrice);
		orderMap.put("goodsPrice", goodsPrice);
		orderMap.put("status", 1);
		Integer r3 = matchOrderDAO.createMatchOrder(orderMap);
//		if(r3 == null || r3 == 0){
//			throw new Exception();
//		}
		
		/**
		 * 创建订单商品
		 */
		if(ValidatorHelper.isNotEmpty(goodsStr)){
			String goodsIds = getGoodsIds(goodsStr);
			if(ValidatorHelper.isEmpty(goodsIds)){
				throw new Exception();
			}
			
			List goodsList = matchGoodsDAO.queryUserSelectGoodsList(ticketId, goodsIds);
			if(goodsList != null){
				Map orderGoodsMap = null;
				for(int i =0 ;i<goodsList.size();i++){
	 				HashMap map = (HashMap) goodsList.get(i);
					long matchGoodsId = Long.parseLong(map.get("match_goods_id").toString());
	 				
	 				/**
					 * 判断获取用户选择的商品数量
					 */
					String [] spiltA = goodsStr.split("\\|");
					int goodsNumber = 1;
					if(ValidatorHelper.isNotEmpty(spiltA)){
						for(String strA : spiltA){
							String [] spiltB = strA.split("\\@");
							if(ValidatorHelper.isNumber(spiltB[0])
									&& ValidatorHelper.isNumber(spiltB[1])){
								long mgId = Long.parseLong(spiltB[0]);
								int number = Integer.parseInt(spiltB[1]);
								if(number < 0){
									number = 1;
								}
								if(number > 99){
									number = 99;
								}
								if(matchGoodsId == mgId){
									goodsNumber = number;
								}
							}
						}
					}		
	 				
	 				
	 				
	 				orderGoodsMap = new HashMap();
	 				orderGoodsMap.put("id", idWorker.nextId());
	 				orderGoodsMap.put("matchOrderId", orderId);
	 				orderGoodsMap.put("matchGoodsId", Long.parseLong(map.get("match_goods_id").toString()));
	 				orderGoodsMap.put("number", goodsNumber);
	 				orderGoodsMap.put("price", Double.parseDouble(map.get("price").toString()));
	 				Integer r4 = matchOrderDAO.createMatchOrderGoods(orderGoodsMap);
//	 				if(r4 == null || r4 == 0){
//	 					throw new Exception();
//	 				}
	 				
				}
			}
		}
		
		return orderId;
	}

	
	private String getGoodsIds(String goodsStr){ 
		if(ValidatorHelper.isEmpty(goodsStr)){
			 return null;
		}
		
		StringBuffer sb = new StringBuffer();
		String [] spiltA = goodsStr.split("\\|");
		if(spiltA != null){
			for(String strA : spiltA){
				String [] spiltB = strA.split("\\@");
				if(ValidatorHelper.isNumber(spiltB[0])){
					sb.append(spiltB[0]).append(",");
				}
			}
			String str = sb.toString();
			if(ValidatorHelper.isNotEmpty(str)){
				if(str.substring(str.length()-1,str.length()).equals(",")){
					str = str.substring(0, str.length()-1);
				}
				return str;
			}
		}
		
		return null;
	}

	@Override
	public Boolean checkOrderPay(Long orderId) {
		MatchOrder order = matchOrderDAO.getById(orderId);
		if(order != null && order.getStatus().intValue() == 2){
			return true;
		}
		return false;
	}


	@Override
	public Boolean updateOrderPay(Long orderId) throws Exception {
		Integer result = matchOrderDAO.updateOrderPay(orderId);
		if(result == null || result == 0){
			return false;
		}
		return true;
	}


	@Override
	public List queryUserOrderList(Long matchId, Long userId, Integer status) {
		List list = matchOrderDAO.queryUserOrderList(matchId, userId, status);
		if(list != null){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
 				HashMap map = (HashMap) list.get(i);
 				
 				/**
 				 * 时间
 				 */
 				try {
					map.put("create_time", DateUtil.convertDateToString(
									DateUtil.convertStringTODate(map.get("create_time").toString(), "yyyy-MM-dd HH:mm")
										,"yyyy-MM-dd HH:mm"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
 				
 				/**
 				 * 拆分订单商品列表
 				 */
 				String concatStr = map.get("concat_str").toString();
 				if(ValidatorHelper.isNotEmpty(concatStr)){
 					List goodsList = new ArrayList();
 					Map goodsMap = null;
 					String [] spiltA = concatStr.split("\\|");
 					if(ValidatorHelper.isNotEmpty(spiltA)){
 						for(String strA : spiltA){
 							String [] spiltB = strA.split("\\@");
 							if(ValidatorHelper.isNotEmpty(spiltB)){
 								goodsMap = new HashMap();
 								String name = spiltB[0];
 								String imgUrl = spiltB[1];
 								int number = Integer.parseInt(spiltB[2]);
 								double price = Double.parseDouble(spiltB[3]);
 								
 								goodsMap.put("name", name);
 								//goodsMap.put("img_url", imgUrl);
 								goodsMap.put("number", number);
 								goodsMap.put("price", CommonHelper.getDobule(price));
 								goodsMap.put("total_price", CommonHelper.getDobule(price * number));
 								goodsList.add(goodsMap);
 							}
 						}
 					}		
 					if(goodsList != null){
 						map.put("goods_list", goodsList);
 					}
 				}
		 		newList.add(map);
			}
			return newList;
		}
		return null;
	}
	
//	@Override
//	public Long createMatchOrder(Long userId,Long matchId,String phone,Double price
//			,String name,Integer sex,String birth,String realName,String certificate
//			,String contactName,String contactPhone,Long smsId
//			,Long matchCodeId,Integer isPay,Long inviteUserId) {
//		
//		/**
//		 * 创建报名订单
//		 */
//		MatchOrder matchOrder = new MatchOrder();
//		Long id = IdWorker.getId();
//		matchOrder.setId(id);
//		matchOrder.setUserId(userId);
//		matchOrder.setMatchId(matchId);
//		matchOrder.setInviteUserId(inviteUserId);
//		matchOrder.setPhone(phone);
//		matchOrder.setPayPrice(price);
//		matchOrder.setPrice(price);
//		matchOrder.setName(CommonHelper.filterEmoji(name));
//		matchOrder.setRealName(realName);
//		matchOrder.setSex(sex);
//		matchOrder.setCertificate(certificate);
//		matchOrder.setContactName(contactName);
//		matchOrder.setContactPhone(contactPhone);
//		matchOrder.setType(1);
//		/**
//		 * 判断邀请码是否为空是否需要支付
//		 * 0 不需要支付 
//		 * 1 需要支付
//		 */
//		if(isPay != null){
//			if(isPay.intValue() == 0){
//				matchOrder.setStatus(2);
//			}else{
//				matchOrder.setStatus(1);
//			}
//		}else{
//			matchOrder.setStatus(1);
//		}
//		matchOrder.setMatchCodeId(matchCodeId);
//		matchOrderDAO.create(matchOrder);
//		
//		/**
//		 * 更新用户信息
//		 */
//		userDAO.updateUser(userId, birth,name);
//		
//		/**
//		 * 修改验证码已失效
//		 */
//		userSmsDAO.updateCodeExpire(smsId);
//		
//		return id;
//	}
//	
//	@Override
//	public void createMatchWatchOrder(Long userId, Long matchId, String phone,
//			Double price, String name,Integer sex, Long smsId,Long inviteUserId,String channel) {
//		
//		/**
//		 * 创建报名订单
//		 */
//		MatchOrder matchOrder = new MatchOrder();
//		matchOrder.setId(IdWorker.getId());
//		matchOrder.setUserId(userId);
//		matchOrder.setMatchId(matchId);
//		matchOrder.setInviteUserId(inviteUserId);
//		matchOrder.setChannel(channel);
//		matchOrder.setPhone(phone);
//		matchOrder.setPayPrice(price);
//		matchOrder.setPrice(price);
//		matchOrder.setName(name);
//		matchOrder.setSex(sex);
//		matchOrder.setType(2);
//		matchOrder.setStatus(2);
//		matchOrderDAO.create(matchOrder);
//		
//		
//		/**
//		 * 修改验证码已失效
//		 */
//		userSmsDAO.updateCodeExpire(smsId);
//	}
//	
//	@Override
//	public Long createMatchGongyiOrder(Long userId, Long matchId, String phone,
//			Double price, String name,Integer sex, Long smsId,Long inviteUserId) {
//		
//		/**
//		 * 创建报名订单
//		 */
//		MatchOrder matchOrder = new MatchOrder();
//		Long id = IdWorker.getId();
//		matchOrder.setId(id);
//		matchOrder.setUserId(userId);
//		matchOrder.setMatchId(matchId);
//		matchOrder.setInviteUserId(inviteUserId);
//		matchOrder.setPhone(phone);
//		matchOrder.setPayPrice(price);
//		matchOrder.setPrice(price);
//		matchOrder.setName(name);
//		matchOrder.setSex(sex);
//		matchOrder.setType(3);
//		matchOrder.setStatus(1);
//		matchOrderDAO.create(matchOrder);
//		
//		
//		/**
//		 * 修改验证码已失效
//		 */
//		userSmsDAO.updateCodeExpire(smsId);
//		return id;
//	}
//
//	
//	
//	@Override
//	public boolean verify(String phone, Long userId, Long matchId,Integer type) {
//		Integer result = matchOrderDAO.getIsSignUp(matchId, userId, phone,type);
//		if(result != null && result > 0){
//			return true;
//		}
//		return false;
//	}
//
//	@Override
//	public Map getOrderDetail(Long orderId,Long userId) {
//		Map map = matchOrderDAO.getOrderDetail(orderId, userId);
//		if(map != null){
//			
//			map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url"));
//			map.put("pay_price", CommonHelper.getDobule(Double.parseDouble(map.get("pay_price").toString())));
//		}
//		return map;
//	}
//
//	@Override
//	public boolean orderWeChatPay(Long id) {
//		MatchOrder order = matchOrderDAO.getById(id);
//		if(order != null && order.getStatus() == 1){
//			return true;
//		}
//		return false;
//	}
//
//	@Override
//	public void payOrder(Long orderId ,Long inviteUserId) throws Exception {	
//		
//		/**
//		 * 修改订单已支付
//		 */
//		matchOrderDAO.updateStatus(orderId,2);
//		
////		/**
////		 *  用户订单信息
////		 */
////		MatchOrder order = matchOrderDAO.getById(orderId);
////		if(order == null){
////			return;
////		}
//		
////		/**
////		 * 微信信息
////		 */
////		UserWeChat wechat = userWeChatDAO.getByUserId(order.getUserId());
////		if(wechat != null){
////			
////			/**
////			 * 推送购买成功模板
////			 */
////			String goUrl = "http://wchat.mperfit.com/";
////			WeChatMessageHelper.sendMatchSignUpSuccess(wechat.getOpenId(), goUrl);
////		}
//		
////		/**
////		 * 判断是否邀请
////		 * 是邀请，两个人组队成功
////		 */
////		if(inviteUserId != null){
////			
////			/**
////			 * 邀请人的报名信息
////			 */
////			MatchOrder inviteOrder = matchOrderDAO.getByMatchIdAndUserId(order.getMatchId(), inviteUserId, null);
////			if(inviteOrder==null){
////				return;
////			}
////			
////			/**
////			 * 判断两个人是否是男女组队
////			 */
////			if(order.getSex() == inviteOrder.getSex()){
////				return;
////			}
////			
////			/**
////			 * 判断邀请人是否已经组队
////			 */
////			Map groupUserMap = matchGroupDAO.getMatchGroupUser(order.getMatchId(), inviteUserId);
////			if(groupUserMap != null){
////				return;
////			}
////			
////			IdWorker idWorker = new IdWorker(0, 0);
////			/**
////			 * 新建组队信息
////			 */
////			MatchGroup group = new MatchGroup();
////			Long groupId = idWorker.nextId();
////			group.setId(groupId);
////			group.setMatchId(order.getMatchId());
////			group.setUserId(inviteUserId);
////			
////			matchGroupDAO.create(group);
////			matchGroupDAO.createMatchGroupUser(idWorker.nextId(),groupId
////					,inviteUserId,order.getMatchId());
////			matchGroupDAO.createMatchGroupUser(idWorker.nextId(),groupId
////					,order.getUserId(),order.getMatchId());
////		}
//		
//	}
//
//	@Override
//	public Integer getMatchCodeCount(Long matchId, Long matchCodeId) {
//		Integer result = matchOrderDAO.getMatchCodeCount(matchId, matchCodeId);
//		if(result != null && result > 0){
//			return result;
//		}
//		return 0;
//	}
//
//	@Override
//	public Map getUserMatchOrder(Long userId,Long matchId,Integer type) {
//		return matchOrderDAO.getUserMatchOrder(userId,matchId,type);
//	}
//
//	@Override
//	public List queryOrderPayUserList(Long matchId, Long userId, String name,
//			Integer sortType, Integer page, Integer pageSize) {
//		return matchOrderDAO.queryOrderPayUserList(matchId, userId, name, sortType, page, pageSize);
//	}
//
//	@Override
//	public Integer getOrderPayUserCount(Long matchId, String name) {
//		return matchOrderDAO.getOrderPayUserCount(matchId, name);
//	}
//
//	@Override
//	public Map getUserMatchOrderTest(Long userId, Long matchId, Integer type,
//			Integer start,Integer end) {
//		return matchOrderDAO.getUserMatchOrderTest(userId, matchId, type, start, end);
//	}
//
//	@Override
//	public Integer getMatchGongyi(Long matchId, Long userId) {
//		return matchOrderDAO.getMatchGongyi(matchId, userId);
//	}
//
//	@Override
//	public Integer getUserMatchCount(Long matchId, Long userId) {
//		return matchOrderDAO.getUserMatchCount(matchId, userId);
//	}

}
