package com.wdd.studentmanager.controller;

import com.wdd.studentmanager.domain.SelectedCourse;
import com.wdd.studentmanager.service.SelectedCourseService;
import com.wdd.studentmanager.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname SelectedCourseController
 * @Description 选课信息控制器
 * @Date 2019/6/30 10:39
 * @Created by WDD
 */
@Controller
@RequestMapping("/selectedCourse")
public class SelectedCourseController {

    @Autowired
    private SelectedCourseService selectedCourseService;


    @GetMapping("/selectedCourse_list")
    public String selectedCourseList(){
        return "course/selectedCourseList";
    }

    /**
     * 异步加载选课信息列表
     * @param page
     * @param rows
     * @param studentid
     * @param courseid
     * @param from
     * @return
     */
    @PostMapping("/getSelectedCourseList")
    @ResponseBody
    public Object getClazzList(@RequestParam(value = "page", defaultValue = "1")Integer page,
                               @RequestParam(value = "rows", defaultValue = "100")Integer rows,
                               @RequestParam(value = "teacherid", defaultValue = "0")String studentid,
                               @RequestParam(value = "teacherid", defaultValue = "0")String courseid ,String from){
        Map<String,Object> paramMap = new HashMap();
        paramMap.put("pageno",page);
        paramMap.put("pagesize",rows);
        if(!studentid.equals("0"))  paramMap.put("studentId",studentid);
        if(!courseid.equals("0"))  paramMap.put("courseId",courseid);
        PageBean<SelectedCourse> pageBean = selectedCourseService.queryPage(paramMap);
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
