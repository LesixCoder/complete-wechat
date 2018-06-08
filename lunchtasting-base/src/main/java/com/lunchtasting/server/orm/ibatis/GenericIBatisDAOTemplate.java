package com.lunchtasting.server.orm.ibatis;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.lunchtasting.server.model.PKModel;


public abstract class GenericIBatisDAOTemplate<PK extends Serializable, T extends PKModel<PK>> extends SqlMapClientDaoSupport {

	final static Map<String, String> ALIAS_MAP = new HashMap<String, String>();

	protected static final String ASC = "ASC";
	protected static final String DESC = "DESC";
	protected static final String ORDER_STR = "orderStr";
	protected static final String MAX_RESULTS = "maxResults";
	protected static final String START_ROW_NUM = "startRowNum";
	protected static final String MAX_ROW_NUM = "maxRowNum";

	protected abstract Class<?> getObjectClass();

	protected abstract String getTableName();

	protected String getNamespace() {
		return getObjectClass().getName();
	}

	protected enum FieldEnum {
		ID("id"), NAME("name");
		FieldEnum(String val) {
			this.val = val;
		}

		String val;

		public String getValue() {
			return this.val;
		}
	}

	public void create(T t) throws DAOException {
		if (t == null) {
			return;
		}
		PK id = (PK) getSqlMapClientTemplate().insert(getNamespace() + ".create", t);
		if (t.getId() == null) {
			t.setId(id);
		}
	}

	//
	public T getById(PK id) {
		if (id == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(FieldEnum.ID.getValue(), id);
		//T model = getByPropertys(map);
		T model = (T) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getById", map);
		return model;
	}

	public T getByProperty(String key, Object value) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, value);
		T model = getByPropertys(map);
		return model;
	}

	public T getByPropertys(Map<String, Object> map) {
		T model = (T) getSqlMapClientTemplate().queryForObject(getNamespace() + ".getByPropertys", map);
		return model;
	}

	public void remove(T t) {
		if (t == null) {
			return;
		}
		getSqlMapClientTemplate().delete(getNamespace() + ".remove", t.getId());
	}

	public void removeByProperty(String property, Object value) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(property, value);
		getSqlMapClientTemplate().delete(getNamespace() + ".remove", map);
	}

	public void update(T model) {
		if (model == null) {
			return;
		}
		getSqlMapClientTemplate().update(getNamespace() + ".update", model);
	}
}