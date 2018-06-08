/**
 *  This is the project code for Talicai . If you want to know more please visit 
 *
 *	         http://www.talicai.com
 *  
 */

package com.lunchtasting.server.util;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.util.Assert;


/**
 * <p>
 * 
 * </p>
 * 
 * @author xuqian
 * 
 * @date 2013-7-22 - 下午6:40:08
 * 
 * 
 */
public final class IbatisHelper<Q> {
	
	public static <Q> List<Q> queryForList(SqlMapClientTemplate sqlMapClientTemplate, String queryName, Map<String, Object> map) {
	
		Assert.notNull(sqlMapClientTemplate, "sqlMapClientTemplate could not be null");
		Assert.notNull(queryName, "queryName could not be null");
		if (map == null || map.isEmpty()) {
			return sqlMapClientTemplate.queryForList(queryName);
		}
		return sqlMapClientTemplate.queryForList(queryName, map);
	}
	
	public static <Q> Q queryForObject(SqlMapClientTemplate sqlMapClientTemplate, String queryName, Map<String, Object> map) {
	
		Assert.notNull(sqlMapClientTemplate, "sqlMapClientTemplate could not be null");
		Assert.notNull(queryName, "queryName could not be null");
		if (map == null || map.isEmpty()) {
			return (Q) sqlMapClientTemplate.queryForObject(queryName);
		}
		return (Q) sqlMapClientTemplate.queryForObject(queryName, map);
	}
}
