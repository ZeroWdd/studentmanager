package com.wdd.studentmanager.controller;

import com.wdd.studentmanager.domain.Course;
import com.wdd.studentmanager.service.CourseService;
import com.wdd.studentmanager.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname CourseController
 * @Description None
 * @Date 2019/6/29 20:02
 * @Created by WDD
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/course_list")
    public String courseList(){
        return "course/courseList";
    }

    @PostMapping("/getCourseList")
    @ResponseBody
    public Object getClazzList(@RequestParam(value = "page", defaultValue = "1")Integer page,
                               @RequestParam(value = "rows", defaultValue = "100")Integer rows, String name,String teacherid ,String from){
        Map<String,Object> paramMap = new HashMap();
        paramMap.put("pageno",page);
        paramMap.put("pagesize",rows);
        if(!StringUtils.isEmpty(name))  paramMap.put("name",name);
        if(!StringUtils.isEmpty(teacherid))  paramMap.put("teacherId",teacherid);
        PageBean<Course> pageBean = courseService.queryPage(paramMap);
        if(!StringUtils.isEmpty(from) && from.equals("combox")){
            return pageBean.getDatas();
        }else{
            Map<String,Object> result = new HashMap();
            result.put("total",pageBean.getTotalsize());
            result.put("rows",pageBean.getDatas());
            return result;
        }
    }
}
