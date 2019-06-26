package com.wdd.studentmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname StudentController
 * @Description None
 * @Date 2019/6/25 20:00
 * @Created by WDD
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    @GetMapping("/student_list")
    public String studentList(){
        return "/student/studentList";
    }

}
