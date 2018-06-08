package com.lunchtasting.server.biz.match.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.match.MatchBIZ;
import com.lunchtasting.server.config.SysConfig;
import com.lunchtasting.server.dao.match.MatchDAO;
import com.lunchtasting.server.dao.user.UserDAO;
import com.lunchtasting.server.helper.CommonHelper;
import com.lunchtasting.server.po.lt.Match;
import com.lunchtasting.server.qiniu.QiNiuStorageHelper;
import com.lunchtasting.server.util.DateUtil;
import com.lunchtasting.server.util.ValidatorHelper;
@Service
public class MatchBIZImpl implements MatchBIZ{
	
	@Autowired
	private MatchDAO  matchDAO;
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public Map getMatchDetail(Long matchId) throws Exception {
		Map map = matchDAO.getMatchDetail(matchId);
		if(map != null){
			
			/**
			 * 时间
			 */
			map.put("time",CommonHelper.getActivityTime(
					map.get("begin_time").toString(), map.get("end_time").toString(),2));
			
			/**
			 * 图片
			 */
			map.put("img_url", SysConfig.IMG_VISIT_URL+map.get("img_url")
					+ QiNiuStorageHelper.MODEL1+"w/750/h/540");
			
			/**
			 * 判断报名状态
			 * 1 可以报名  2已经报名  3报名结束
			 */
/*			Date endTime = DateUtil.convertStringTODate(map.get("end_time").toString(),DateUtil.YYYY_MM_DD_HH_MM_SS);
			Date nowTime = new Date();
			if(nowTime.after(endTime)){
				map.put("enroll_status", 3);
			}else{
				if(ValidatorHelper.isMapNotEmpty(map.get("is_enroll"))){
					map.put("enroll_status", 2);
				}else{
					int enrollNum = Integer.parseInt(map.get("enroll_num").toString());
					int nowEnrollNum = Integer.parseInt(map.get("now_enroll_num").toString());
					if(nowEnrollNum >= enrollNum){
						map.put("enroll_status", 3);
					}else{
						map.put("enroll_status", 1);
					}				
				}
			}*/
			
			map.remove("begin_time");
			map.remove("end_time");
		}
		return map;
	}

}
