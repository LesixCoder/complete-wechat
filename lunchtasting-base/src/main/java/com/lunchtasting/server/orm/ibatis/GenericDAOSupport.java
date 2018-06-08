package com.lunchtasting.server.orm.ibatis;

import java.io.Serializable;

import javax.annotation.Resource;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.lunchtasting.server.model.PKModel;

/**
 * @param <PK>
 * @param <T>
 * @author xq
 */
public abstract class GenericDAOSupport<PK extends Serializable, T extends PKModel<PK>>
		extends GenericIBatisDAOTemplate<PK, T> {

	@Resource(name = "sqlMapClient")
	public void setSqlMapClientFactory(SqlMapClient sqlMapClient) {
		this.setSqlMapClient(sqlMapClient);
	}
}
