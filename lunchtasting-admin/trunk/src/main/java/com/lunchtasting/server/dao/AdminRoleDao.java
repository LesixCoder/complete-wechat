package com.lunchtasting.server.dao;

import java.util.HashMap;
import java.util.List;
import com.lunchtasting.server.orm.ibatis.GenericDAO;
import com.lunchtasting.server.po.lt.SysRole;

public interface AdminRoleDao extends GenericDAO<SysRole>{
	List<SysRole> queryRoleList(HashMap map);
}
