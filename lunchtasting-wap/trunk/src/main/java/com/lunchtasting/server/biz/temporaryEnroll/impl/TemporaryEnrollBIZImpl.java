package com.lunchtasting.server.biz.temporaryEnroll.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.avro.mapred.tether.TetherJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.temporaryEnroll.TemporaryEnrollBIZ;
import com.lunchtasting.server.dao.temporaryEnroll.EnrollGroupDAO;
import com.lunchtasting.server.dao.temporaryEnroll.TemporaryEnrollDAO;
import com.lunchtasting.server.po.temporaryEnroll.EnrollGroup;
import com.lunchtasting.server.po.temporaryEnroll.TemporaryEnroll;
import com.lunchtasting.server.util.IdWorker;

@Service
public class TemporaryEnrollBIZImpl implements TemporaryEnrollBIZ{
	@Autowired
	private TemporaryEnrollDAO temporaryEnrollDAO;
	@Autowired
	private EnrollGroupDAO enrollGroupDAO;

	@Override
	public Boolean create(TemporaryEnroll temporaryEnroll) {
		// TODO Auto-generated method stub
		temporaryEnroll.setId(IdWorker.getId()); 
		temporaryEnrollDAO.create(temporaryEnroll);
		if(temporaryEnroll.getId()!=null){
			return true;
		}
		return false;
	}

	@Override
	public TemporaryEnroll getRandomOneRandom(HashMap map) {
		// TODO Auto-generated method stub
		return temporaryEnrollDAO.getRandomOneRandom(map);
	}

	@Override
	public Long applyTo(Long otherId,Integer sex ,String name,String tel,Long userId) {
		//新建小组
		EnrollGroup enrollGroup = new EnrollGroup();
		enrollGroup.setId(IdWorker.getId());
		enrollGroupDAO.create(enrollGroup);
		//新建报名用户
		TemporaryEnroll temporaryEnroll = new TemporaryEnroll();
		temporaryEnroll.setId(IdWorker.getId());
		temporaryEnroll.setGroupId(enrollGroup.getId());
		temporaryEnroll.setName(name);
		temporaryEnroll.setSex(sex);
		temporaryEnroll.setState(1);
		temporaryEnroll.setUserId(userId);
		temporaryEnroll.setPhone(tel);
		temporaryEnrollDAO.create(temporaryEnroll);
		//修改原先用户
		TemporaryEnroll otherEnroll = new TemporaryEnroll();
		otherEnroll.setGroupId(enrollGroup.getId());
		otherEnroll.setState(1);
		otherEnroll.setUserId(otherId);
		temporaryEnrollDAO.updateTemporaryEnroll(otherEnroll);
		//返回报名id
		return temporaryEnroll.getId();
	}
	@Override
	public Long applyOne(Integer sex ,String name ,String tel,Long userId) {
		TemporaryEnroll temporaryEnroll = new TemporaryEnroll();
		temporaryEnroll.setId(IdWorker.getId());
		temporaryEnroll.setName(name);
		temporaryEnroll.setSex(sex);
		temporaryEnroll.setState(0);
		temporaryEnroll.setPhone(tel);
		temporaryEnroll.setUserId(userId);
		temporaryEnrollDAO.create(temporaryEnroll);
		//返回报名id
		return temporaryEnroll.getId();
	}

	@Override
	public boolean payUpdate(Long userId,Map map) {
		if(userId==0 || userId==null){
			return false;
		}
		Long otherId = null;
		if(map.get("otherId")!=null){
			otherId = Long.parseLong(map.get("otherId").toString());
		}
		Integer sex = Integer.parseInt(map.get("sex").toString());
		String phone = map.get("tel").toString();
		String name = map.get("name").toString();
		/**
		 * 单人报名
		 */
		if(otherId==null){
			applyOne(sex, name, phone,userId);
			return true;
		}
		
		//判断是否匹配
		HashMap map1 = new HashMap();
		map1.put("userId",otherId);
		/**
		 * otherId不合法 单人报名
		 */
		TemporaryEnroll otherEnroll = temporaryEnrollDAO.getTemporaryEnrollByMap(map1);
		if(otherEnroll==null){
			applyOne(sex, name, phone,userId);
			return true;
		}
		/**
		 * 用户未匹配
		 */
		if(otherEnroll.getState()!=null && otherEnroll.getState()==0){
			applyTo(otherId, sex, name, phone, userId);
			return true;
		/**
		 * 用户已匹配
		 */
		}else if(otherEnroll.getState()!=null&&otherEnroll.getState()==1){
			applyOne(sex, name, phone,userId);
			return true;
		}
		System.out.println("后台出现问题记录："+userId);
		return false;
	}

	@Override
	public int checkUser(String phone) {
		// TODO Auto-generated method stub
		return temporaryEnrollDAO.checkUser(phone);
	}

	@Override
	public List show(Long uesrId, Long groupId) {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		if(uesrId!=null&&uesrId!=0){
			map.put("userId",uesrId );
		}
		if(groupId!=null&&groupId!=0){
			map.remove("userId");
			map.put("groupId",groupId );
		}
		return temporaryEnrollDAO.show(map);
	}

	@Override
	public TemporaryEnroll getTemporaryEnrollByUserId(Long userId) {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		if(userId!=null&&userId!=0){
			map.put("userId",userId );
		}
		return temporaryEnrollDAO.getTemporaryEnrollByMap(map);
	}
	@Override
	public boolean verifySex(Integer sex,Long userId) {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		map.put("userId",userId );
		TemporaryEnroll temporaryEnroll =temporaryEnrollDAO.getTemporaryEnrollByMap(map);
		if(temporaryEnroll==null){
			return false;
		}
		if((int)temporaryEnroll.getSex()==(int)sex){
			return true;
		}
		return false;
	}
	@Override
	public boolean applyhaha(Long userId1,Long userId2) {
		HashMap map1 = new HashMap();
		map1.put("userId",userId1);
		TemporaryEnroll user1 = temporaryEnrollDAO.getTemporaryEnrollByMap(map1);
		
		HashMap map2 = new HashMap();
		map2.put("userId",userId2);
		TemporaryEnroll user2 = temporaryEnrollDAO.getTemporaryEnrollByMap(map2);
		if(user1==null || user2==null || user1.getState()==1||user2.getState()==1 || user1.getSex().equals(user2.getSex())){
			System.out.println("失败");
			return false;
		}
		try {
			EnrollGroup enrollGroup = new EnrollGroup();
			enrollGroup.setId(IdWorker.getId());
			enrollGroupDAO.create(enrollGroup);
			user1.setState(1);
			user1.setGroupId(enrollGroup.getId());
			user2.setState(1);
			user2.setGroupId(enrollGroup.getId());
			temporaryEnrollDAO.updateTemporaryEnroll(user1);
			temporaryEnrollDAO.updateTemporaryEnroll(user2);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
