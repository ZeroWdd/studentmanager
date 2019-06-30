package com.wdd.studentmanager.service;

import com.wdd.studentmanager.domain.Course;
import com.wdd.studentmanager.util.PageBean;

import java.util.Map;

/**
 * @Classname CourseService
 * @Description None
 * @Date 2019/6/29 20:09
 * @Created by WDD
 */
public interface CourseService {
    PageBean<Course> queryPage(Map<String, Object> paramMap);
}
