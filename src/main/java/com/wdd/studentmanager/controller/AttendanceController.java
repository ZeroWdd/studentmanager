package com.wdd.studentmanager.controller;

import com.wdd.studentmanager.domain.Attendance;
import com.wdd.studentmanager.domain.Course;
import com.wdd.studentmanager.domain.SelectedCourse;
import com.wdd.studentmanager.domain.Student;
import com.wdd.studentmanager.service.AttendanceService;
import com.wdd.studentmanager.service.CourseService;
import com.wdd.studentmanager.service.SelectedCourseService;
import com.wdd.studentmanager.util.Const;
import com.wdd.studentmanager.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname AttendanceController
 * @Description None
 * @Date 2019/7/1 11:57
 * @Created by WDD
 */
@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private SelectedCourseService selectedCourseService;
    @Autowired
    private CourseService courseService;

    @GetMapping("/attendance_list")
    public String attendanceList(){
        return "/attendance/attendanceList";
    }


    /**
     * 异步获取考勤列表数据
     * @param page
     * @param rows
     * @param studentid
     * @param courseid
     * @param type
     * @param date
     * @param from
     * @param session
     * @return
     */
    @RequestMapping("/getAttendanceList")
    @ResponseBody
    public Object getAttendanceList(@RequestParam(value = "page", defaultValue = "1")Integer page,
                                 @RequestParam(value = "rows", defaultValue = "100")Integer rows,
                                 @RequestParam(value = "studentid", defaultValue = "0")String studentid,
                                 @RequestParam(value = "courseid", defaultValue = "0")String courseid,
                                 String type,String date, String from, HttpSession session){
        Map<String,Object> paramMap = new HashMap();
        paramMap.put("pageno",page);
        paramMap.put("pagesize",rows);
        if(!studentid.equals("0"))  paramMap.put("studentid",studentid);
        if(!courseid.equals("0"))  paramMap.put("courseid",courseid);
        if(!StringUtils.isEmpty(type))  paramMap.put("type",type);
        if(!StringUtils.isEmpty(date))  paramMap.put("date",date);

        //判断是老师还是学生权限
        Student student = (Student) session.getAttribute(Const.STUDENT);
        if(!StringUtils.isEmpty(student)){
            //是学生权限，只能查询自己的信息
            paramMap.put("studentid",student.getId());
        }
        PageBean<Attendance> pageBean = attendanceService.queryPage(paramMap);
        if(!StringUtils.isEmpty(from) && from.equals("combox")){
            return pageBean.getDatas();
        }else{
            Map<String,Object> result = new HashMap();
            result.put("total",pageBean.getTotalsize());
            result.put("rows",pageBean.getDatas());
            return result;
        }
    }

    /**
     * 通过 选课信息中的课程id 查询 学生所选择的课程
     * @param studentid
     * @return
     */
    @RequestMapping("/getStudentSelectedCourseList")
    @ResponseBody
    public Object getStudentSelectedCourseList(@RequestParam(value = "studentid", defaultValue = "0")String studentid){
        //通过学生id 查询 选课信息
        List<SelectedCourse> selectedCourseList = selectedCourseService.getAllBySid(Integer.parseInt(studentid));
        //通过 选课信息中的课程id 查询 学生所选择的课程
        List<Integer> ids = new ArrayList<>();
        for(SelectedCourse selectedCourse : selectedCourseList){
            ids.add(selectedCourse.getCourseId());
        }
        List<Course> courseList = courseService.getCourseById(ids);
        return courseList;
    }


}
