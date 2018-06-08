package com.lunchtasting.server.biz.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.common.CommonCollectBIZ;
import com.lunchtasting.server.dao.common.CommonCollectDAO;
import com.lunchtasting.server.po.lt.CommonCollect;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class CommonCollectBIZImpl implements CommonCollectBIZ{
	
	@Autowired
	private CommonCollectDAO collectDAO;

	@Override
	public Boolean createCollect(Long userId, Long bizId, Integer bizType) {
		
		CommonCollect collect = new CommonCollect();
		collect.setUserId(userId);
		collect.setBizId(bizId);
		collect.setBizType(bizType);
		collectDAO.create(collect);
		if(ValidatorHelper.isEmpty(collect.getId())){
			return false;
		}
		return true;
	}

	@Override
	public Boolean checkUserCollect(Long userId, Long bizId, Integer bizType) {
		Long collectId = collectDAO.getCollectId(userId, bizId, bizType);
		if(collectId == null){
			return false;
		}
		return true;
	}
}
