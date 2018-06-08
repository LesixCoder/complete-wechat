package com.lunchtasting.server.biz.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.common.CommonPraiseBIZ;
import com.lunchtasting.server.dao.common.CommonPraiseDAO;
import com.lunchtasting.server.po.lt.CommonPraise;
import com.lunchtasting.server.util.IdWorker;
import com.lunchtasting.server.util.ValidatorHelper;

@Service
public class CommonPraiseBIZImpl implements CommonPraiseBIZ {

	@Autowired
	private CommonPraiseDAO praiseDAO;

	@Override
	public Boolean createPraise(Long userId, Long bizId, Integer bizType) {
		
		CommonPraise praise = new CommonPraise();
		praise.setId(IdWorker.getId());
		praise.setUserId(userId);
		praise.setBizId(bizId);
		praise.setBizType(bizType);
		praiseDAO.create(praise);
		if(ValidatorHelper.isEmpty(praise.getId())){
			return false;
		}
		return true;
	}

	@Override
	public Boolean checkUserPraise(Long userId, Long bizId, Integer bizType) {
		Long praiseId = praiseDAO.getPraiseId(userId, bizId, bizType);
		if(praiseId == null){
			return false;
		}
		return true;
	}
}
