package com.lunchtasting.server.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.MatchScoreDAO;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.MatchScore;
import com.lunchtasting.server.util.IdWorker;
@Repository
public class MatchScoreDAOImpl extends GenericDAOSupport<Long,MatchScore> implements MatchScoreDAO{

	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return MatchScore.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "match_score";
	}

	@Override
	public Integer addUserScore(Long userId, String tel, Long matchId,
			Integer score,Integer bizType) {
		// TODO Auto-generated method stub
		int type = 1;
		if(score<0){
			type=2;
		}
		HashMap map = new HashMap();
		map.put("id",IdWorker.getId());
		map.put("userId",userId);
		map.put("tel",tel);
		map.put("type",type);
		map.put("matchId",matchId);
		map.put("score",score);
		map.put("bizType",bizType);
		return (Integer) getSqlMapClientTemplate().insert(getNamespace() + ".addUserScore",map);
	}

}
