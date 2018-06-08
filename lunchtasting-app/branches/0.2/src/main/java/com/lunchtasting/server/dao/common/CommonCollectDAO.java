package com.lunchtasting.server.dao.common;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.CommonCollect;

public interface CommonCollectDAO extends GenericDAO<CommonCollect> {

	Long getCollectId(Long userId, Long bizId, Integer bizType);
}
