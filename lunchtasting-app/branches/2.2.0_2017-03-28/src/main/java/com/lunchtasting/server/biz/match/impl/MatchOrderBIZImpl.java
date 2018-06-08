package com.lunchtasting.server.biz.match.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.match.MatchOrderBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.match.MatchOrderDAO;
import com.lunchtasting.server.dao.user.UserHotDAO;
import com.lunchtasting.server.enumeration.SysEnum;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.po.lt.MatchOrder;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class MatchOrderBIZImpl implements MatchOrderBIZ {

	@Autowired
	private MatchOrderDAO matchOrderDAO;
	@Autowired
	private UserHotDAO userHotDAO;

	@Override
	public Boolean createMatchOrder(Long matchId, Long userId) {
		
		MatchOrder order = new MatchOrder();
		order.setId(IdWorker.getId());
		order.setMatchId(matchId);
		order.setUserId(userId);
		order.setPrice(0d);
		order.setPayPrice(0d);
		order.setStatus(2);
		matchOrderDAO.create(order);
		/**
		 * 3.20 chenchen 添加活跃度
		 */
		userHotDAO.createUserHot(userId, SysEnum.UserHotType.ENROLL_MATCH.getNumber(), SysEnum.UserHotType.ENROLL_MATCH.getType());
		return true;
	}

	@Override
	public Integer getMatchOrderCount(Long matchId) {
		return matchOrderDAO.getMatchOrderCount(matchId);
	}

	@Override
	public Boolean checkUserEnroll(Long matchId, Long userId) {
		if(matchId == null || userId == null){
			return false;
		}
		
		Integer result = matchOrderDAO.getByMatchIdAndUserId(matchId, userId);
		if(result == null || result == 0){
			return false;
		}
		return true;
	}

	@Override
	public Integer getUserMatchOrderCount(Long userId) {
		return matchOrderDAO.getUserMatchOrderCount(userId);
	}

	@Override
	public List queryUserMatchOrderList(Long userId, Integer page,
			Integer pageSize) throws ParseException {
		List list = matchOrderDAO.queryUserMatchOrderList(userId, page, pageSize);
		if(ValidatorHelper.isNotEmpty(list)){
			List newList = new ArrayList();
			for(int i =0 ;i<list.size();i++){
				HashMap map = (HashMap) list.get(i);
				
				/**
				 * 图片
				 */
				map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
						+ QiNiuStorageHelper.MODEL0+"w/330/h/216");
				
				/**
				 * 时间
				 */
				map.put("time",CommonHelper.getActivityTime(
							map.get("begin_time").toString(), map.get("end_time").toString(),1));
			
				
				/**
				 * 状态
				 */
				int status = CommonHelper.getActivityStatus(
						map.get("begin_time").toString(), map.get("end_time").toString());
				map.put("status", status);
				
				map.remove("begin_time");
				map.remove("end_time");
				newList.add(map);
			}
			return newList;
		}
		return list;
	}

}
