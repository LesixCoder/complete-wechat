package com.lunchtasting.server.dao.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.user.UserDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.User;
import com.lunchtasting.server.util.ValidatorHelper;

@Repository
public class UserDAOImpl extends GenericDAOSupport<Long, User> implements UserDAO {

	@Override
	protected Class<?> getObjectClass() {
		return User.class;
	}

	@Override
	protected String getTableName() {
		return "user";
	}
	
	@Override
	public Long getByUserId(Long userId) {
		if(userId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		return (Long) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getByUserId", map);	
	}


	@Override
	public Long getUserIdByPhone(String phone) {
		if(ValidatorHelper.isEmpty(phone)){
			return null;
		}
		
		Map map = new HashMap();
		map.put("phone", phone);
		return (Long) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserIdByPhone", map);	
	}

	@Override
	public Integer updateLoginTime(Long userId) {
		if(ValidatorHelper.isEmpty(userId)){
			return 0;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		return getSqlMapClientTemplate().update(getNamespace() + ".updateLoginTime", map);	
	}

	@Override
	public void createSuggest(Long id,Long userId, String content) {
		if(ValidatorHelper.isEmpty(id) || ValidatorHelper.isEmpty(userId) || ValidatorHelper.isEmpty(content)){
			return;
		}
		
		Map map = new HashMap();
		map.put("id", id);
		map.put("userId", userId);
		map.put("content", content);
		getSqlMapClientTemplate().insert(getNamespace() + ".createSuggest", map);	
	}

	@Override
	public Integer updateUserInfo(Long userId,String name,String feeling,String signature,String hobby,String tags) {
		if(userId == null){
			return 0;
		}
		
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("name", name);
		map.put("feeling", feeling);
		map.put("signature", signature);
		map.put("hobby", hobby);
		map.put("tags", tags);
		return getSqlMapClientTemplate().update(getNamespace() + ".updateUserInfo", map);		
	}

	@Override
	public Map getUserDetail(Long userId) {
		if(userId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		return (Map) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserDetail", map);	
	}

	@Override
	public Map getEditInfo(Long userId) {
		if(userId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		return (Map) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getEditInfo", map);	
	}

	@Override
	public List queryUserVisitorList(Long desUserId, Integer page,
			Integer pageSize) {
		if(desUserId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("desUserId", desUserId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryUserVisitorList", map);	
	}

	@Override
	public List queryUserCommentList(Long userId, Integer page, Integer pageSize) {
		if(userId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("page", page);
		map.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryUserCommentList", map);	
	}

	@Override
	public Integer getUserCommentCount(Long userId) {
		if(userId == null){
			return 0;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getUserCommentCount", map);	
	}

	@Override
	public void createUserComment(Long id, Long userId, Long srcUserId,
			String content, Long parentId, Long parentSrcUserId) {
		if(id == null || userId == null || srcUserId == null || content == null){
			return;
		}
		Map map = new HashMap();
		map.put("id", id);
		map.put("userId", userId);
		map.put("srcUserId", srcUserId);
		map.put("content", content);
		map.put("parentId", parentId);
		map.put("parentSrcUserId", parentSrcUserId);
		getSqlMapClientTemplate().insert(getNamespace() + ".createUserComment", map);	
		
	}

	@Override
	public void createUserDevice(Long id,Long userId) {
		if(id == null || userId == null){
			return;
		}
		Map map = new HashMap();
		map.put("id", id);
		map.put("userId", userId);
		getSqlMapClientTemplate().insert(getNamespace() + ".createUserDevice", map);	
		
	}

	@Override
	public Map getMatchUser(Long userId, Long matchId) {
		if(userId == null || matchId == null){
			return null;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("matchId", matchId);
		return (Map)getSqlMapClientTemplate().queryForObject(getNamespace() + ".getMatchUser", map);	
	}

	@Override
 	public void updateUser(Long userId, String birth,String name) {
		if(userId == null || ValidatorHelper.isEmpty(birth)){
			return;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("birth", birth);
		map.put("name", name);
		getSqlMapClientTemplate().update(getNamespace() + ".updateUser", map);	
		
	}

	@Override
	public void updateImgUrl(Long userId, String imgUrl) {
		if(userId == null || ValidatorHelper.isEmpty(imgUrl)){
			return;
		}
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("imgUrl", imgUrl);
		getSqlMapClientTemplate().update(getNamespace() + ".updateImgUrl", map);
	}

	@Override
	public Integer updateAddDeposit(Long userId, Double money) {
		if(userId == null || money == null){
			return 0;
		}
		
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("money", money);
		return getSqlMapClientTemplate().update(getNamespace() + ".updateAddDeposit", map);	
	}

	@Override
	public Integer updateReduceDeposit(Long userId, Double money) {
		if(userId == null || money == null){
			return 0;
		}
		
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("money", money);
		return getSqlMapClientTemplate().update(getNamespace() + ".updateReduceDeposit", map);	
	}

	@Override
	public void updateInviteUserId(Long userId, Long inviteUserId) {
		if(userId == null || inviteUserId == null){
			return;
		}
		
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("inviteUserId", inviteUserId);
		getSqlMapClientTemplate().update(getNamespace() + ".updateInviteUserId", map);	
	}

}
