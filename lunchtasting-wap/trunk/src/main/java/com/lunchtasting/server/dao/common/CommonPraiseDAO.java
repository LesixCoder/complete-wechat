package com.lunchtasting.server.dao.common;

import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.CommonPraise;

public interface CommonPraiseDAO extends GenericDAO<CommonPraise> {

	Long getPraiseId(Long userId, Long bizId, Integer bizType);

}
