package com.lunchtasting.server.biz;

import java.util.List;

import com.lunchtasting.server.po.lt.CourseTemporary;

public interface AdminCourseTemporaryBIZ {
	Long addCourseTemporary(CourseTemporary courseTemporary);
	List queryCourseTemporary();
}
