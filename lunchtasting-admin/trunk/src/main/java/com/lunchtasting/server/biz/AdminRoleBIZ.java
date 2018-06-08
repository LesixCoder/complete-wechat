package com.lunchtasting.server.biz;

import java.util.HashMap;
import java.util.List;
import com.lunchtasting.server.po.lt.SysRole;

public interface AdminRoleBIZ {
	List<SysRole> queryRoleList(HashMap map);
}
