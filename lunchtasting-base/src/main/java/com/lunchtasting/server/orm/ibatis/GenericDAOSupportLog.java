package com.lunchtasting.server.orm.ibatis;


import java.io.Serializable;

import javax.annotation.Resource;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.lunchtasting.server.model.PKModel;

public abstract class GenericDAOSupportLog<PK extends Serializable, T extends PKModel<PK>>
		extends GenericIBatisDAOTemplate<PK, T> {

	@Resource(name = "sqlMapClient-log")
	public void setSqlMapClientFactory(SqlMapClient sqlMapClient) {
		this.setSqlMapClient(sqlMapClient);
	}
}
