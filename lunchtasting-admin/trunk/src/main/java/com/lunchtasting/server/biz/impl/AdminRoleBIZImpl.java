package com.lunchtasting.server.biz.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lunchtasting.server.biz.AdminRoleBIZ;
import com.lunchtasting.server.dao.AdminRoleDao;
import com.lunchtasting.server.po.lt.SysRole;
@Service
public class AdminRoleBIZImpl implements AdminRoleBIZ{
	@Autowired
    private AdminRoleDao adminRoleDao;
	@Override
	public List<SysRole> queryRoleList(HashMap map) {
		// TODO Auto-generated method stub
		return adminRoleDao.queryRoleList(map);
	}

}
