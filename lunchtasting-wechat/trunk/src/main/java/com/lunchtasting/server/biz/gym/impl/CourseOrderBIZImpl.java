package com.lunchtasting.server.biz.gym.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lunchtasting.server.biz.gym.CourseOrderBIZ;
import com.lunchtasting.server.dao.gym.CourseOrderDAO;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.helper.VariableHelper;
import com.lunchtasting.server.helper.WeChatHelper;
import com.lunchtasting.server.po.lt.CourseOrder;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.DateUtil;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;

@Transactional(rollbackFor = Throwable.class)
@Service
public class CourseOrderBIZImpl implements CourseOrderBIZ {

	@Autowired
	private CourseOrderDAO courseOrderDAO;
	
	@Override
	public CourseOrder getById(Long id) {
		return courseOrderDAO.getById(id);
	}

	@Override
	public List queryOrderList(Long userId, Integer page, Integer pageSize) {
		List list =  courseOrderDAO.queryOrderList(userId, page, pageSize);
		
		if(ValidatorHelper.isNotEmpty(list)){
				
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				
				map.put("coach_img_url", VariableHelper.IMAGE_VISIT_URL+map.get("coach_img_url")
						+ QiNiuStorageHelper.MODEL1+"w/140/h/140");
				
				
				/**
				 * 判断时间显示
				 * 1 普通课  2团课
				 */
				int type = Integer.parseInt(map.get("type").toString());
				if(type == 1){
					map.put("str_time", map.get("begin_time") + " " +map.get("str_time"));
				}else{
					map.put("str_time", map.get("begin_time") + " - " 
							+ map.get("end_time") + " " +map.get("str_time"));
				}
				
				newList.add(map);

			}
			return newList;
		}
		
		return null;
		
	}

	@Override
	public Integer getOrderCount(Long userId) {
		return courseOrderDAO.getOrderCount(userId);
	}

	@Override
	public Long createOrder(Long userId, Long courseId, Long courseMealId,
			Long gymId, Double price, String code,String phone,Integer sex,Integer status) throws Exception {
		
		IdWorker idWorker = new IdWorker(0, 0);
		CourseOrder order = new CourseOrder();
		long orderId = idWorker.nextId();
		order.setId(orderId);
		order.setUserId(userId);
		order.setCourseId(courseId);
		order.setCourseMealId(courseMealId);
		order.setGymId(gymId);
		order.setPrice(price);
		order.setPayPrice(price);
		order.setPhone(phone);
		order.setSex(sex);
		order.setCode(code);
		order.setPayType(1);
		order.setStatus(status);
		courseOrderDAO.create(order);
		return orderId;
	}
	
	@Override
	public void createActivityOrder(Long userId, Long courseId, Long courseMealId,
			Long gymId, Double price, String code,String phone) {
		
		IdWorker idWorker = new IdWorker(0, 0);
		CourseOrder order = new CourseOrder();
		order.setId(idWorker.nextId());
		order.setUserId(userId);
		order.setCourseId(courseId);
		order.setCourseMealId(courseMealId);
		order.setGymId(gymId);
		order.setPrice(price);
		order.setPayPrice(price);
		order.setPhone(phone);
		order.setCode(code);
		order.setPayType(1);
		order.setStatus(2);
		courseOrderDAO.create(order);
	}

	@Override
	public Boolean checkOrderPay(Long orderId) {
		
		CourseOrder order = courseOrderDAO.getById(orderId);
		if(order != null && order.getStatus().intValue() != 1){
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateOrderPay(Long orderId) {
		Integer result = courseOrderDAO.updateOrderPay(orderId);
		if(result == null || result == 0){
			return false;
		}
		return true;
	}

	@Override
	public Map getRefundOrder(Long orderId, Long userId) {
		return courseOrderDAO.getRefundOrder(orderId, userId);
	}

	@Override
	public Boolean refundOrder(Long orderId,Long userId,Double payPrice
			,Double refundPrice,String outTradeNo,Integer type) throws Exception {
		
		/**
		 * 创建退款记录
		 */
		IdWorker idWorker = new IdWorker(0, 0);
		String outRefundNo = CommonHelper.getOutNo();

		Map refundMap = new HashMap();
		refundMap.put("id", idWorker.nextId());
		refundMap.put("userId", userId);
		refundMap.put("orderId", orderId);
		refundMap.put("payPrice", payPrice);
		refundMap.put("refundPrice", refundPrice);
		refundMap.put("outRefundNo", outRefundNo);
		refundMap.put("type", type);
		courseOrderDAO.createOrderRefund(refundMap);
		
		/**
		 * 修改订单状态已退款
		 */
		Integer result = courseOrderDAO.updateOrderRefund(orderId);
		if(result == null || result == 0){
			throw new Exception();
		}
		
		/**
		 * 调微信退款api
		 */
		SortedMap params = WeChatHelper.getRefundSignMap(outTradeNo, outRefundNo, payPrice,refundPrice);
		Map map = WeChatHelper.refund(params);
		if(map == null){
			throw new Exception();
		}
		
		return true;
	}

	@Override
	public Map getCourseOrderDetail(Long userId, Long orderId) {
		Map map = courseOrderDAO.getCourseOrderDetail(userId, orderId);
		if(map != null){
			map.put("pay_price", CommonHelper.getDobule(
						Double.parseDouble(map.get("pay_price").toString())));
			
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
		}
		
		return map;
	}


}
