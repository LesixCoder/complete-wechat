package com.lunchtasting.server.dao.scheduleJob.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lunchtasting.server.dao.scheduleJob.ScheduleJobDAO;
import com.lunchtasting.server.model.BasicPOModel;
import com.lunchtasting.server.orm.ibatis.DAOException;
import com.lunchtasting.server.orm.ibatis.GenericDAOSupport;
import com.lunchtasting.server.po.lt.ScheduleJob;

@Repository
public class ScheduleJobDAOImpl extends GenericDAOSupport<Long,ScheduleJob> implements ScheduleJobDAO{
	@Override
	protected Class<?> getObjectClass() {
		// TODO Auto-generated method stub
		return ScheduleJob.class;
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "schedule_job";
	}
	@Override
	public List<ScheduleJob> getScheduleJobList() {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".getScheduleJobList", map);
	}

	@Override
	public List queryScheduleJobList(HashMap map) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList(getNamespace() + ".queryScheduleJobList", map);
	}

	@Override
	public Integer queryScheduleJobCount(String jobName,String jobGroup) {
		// TODO Auto-generated method stub
		if(jobName!=null && !jobName.equals("")&& jobGroup!=null && !jobGroup.equals("")){
			HashMap map = new HashMap();
			map.put("jobName", jobName);
			map.put("jobGroup", jobGroup);
			return (Integer) getSqlMapClientTemplate().queryForObject(getNamespace() + ".queryScheduleJobCount", map);
		}else{
			return null;
		}
	}
}
