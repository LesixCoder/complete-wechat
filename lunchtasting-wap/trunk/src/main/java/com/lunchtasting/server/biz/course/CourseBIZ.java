package com.lunchtasting.server.biz.course;

import java.util.Map;

public interface CourseBIZ {
	/**
	 * 查询课程详细
	 * @param id
	 * @return 
	 */
	Map getCourseDetail(Long id);
}
