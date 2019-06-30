package com.wdd.studentmanager.controller;

import com.wdd.studentmanager.domain.Admin;
import com.wdd.studentmanager.domain.Student;
import com.wdd.studentmanager.domain.Teacher;
import com.wdd.studentmanager.service.AdminService;
import com.wdd.studentmanager.service.StudentService;
import com.wdd.studentmanager.service.TeacherService;
import com.wdd.studentmanager.util.AjaxResult;
import com.wdd.studentmanager.util.Const;
import com.wdd.studentmanager.util.CpachaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Classname SystemController
 * @Description None
 * @Date 2019/6/24 19:25
 * @Created by WDD
 */
@Controller
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    /**
     * 跳转登录界面
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    /**
     * 登录表单提交 校验
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public AjaxResult submitlogin(String username, String password, String code, String type,
                                  HttpSession session){
        AjaxResult ajaxResult = new AjaxResult();
        if(StringUtils.isEmpty(username)){
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("请填写用户名");
            return ajaxResult;
        }
        if(StringUtils.isEmpty(password)){
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("请填写密码");
            return ajaxResult;
        }
        if(StringUtils.isEmpty(code)){
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("请填验证码");
            return ajaxResult;
        }
        if(StringUtils.isEmpty(session.getAttribute(Const.CODE))){
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("会话时间过长，请刷新");
            return ajaxResult;
        }else{
            if(!code.equalsIgnoreCase((String) session.getAttribute(Const.CODE))){
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("验证码错误");
                return ajaxResult;
            }
        }
        //数据库校验
        switch (type){
            case "1":{ //管理员
                Admin admin = new Admin();
                admin.setPassword(password);
                admin.setUsername(username);
                Admin ad = adminService.findByAdmin(admin);
                if(StringUtils.isEmpty(ad)){
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("用户名或密码错误");
                    return ajaxResult;
                }
                ajaxResult.setSuccess(true);
                session.setAttribute(Const.ADMIN,ad);
                session.setAttribute(Const.USERTYPE,"1");
                break;
            }
            case "2":{
                Student student = new Student();
                student.setPassword(password);
                student.setUsername(username);
                Student st = studentService.findByStudent(student);
                if(StringUtils.isEmpty(st)){
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("用户名或密码错误");
                    return ajaxResult;
                }
                ajaxResult.setSuccess(true);
                session.setAttribute(Const.STUDENT,st);
                session.setAttribute(Const.USERTYPE,"2");
                break;
            }
            case "3":{
                Teacher teacher = new Teacher();
                teacher.setPassword(password);
                teacher.setUsername(username);
                Teacher tr = teacherService.findByTeacher(teacher);
                if(StringUtils.isEmpty(tr)){
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("用户名或密码错误");
                    return ajaxResult;
                }
                ajaxResult.setSuccess(true);
                session.setAttribute(Const.TEACHER,tr);
                session.setAttribute(Const.USERTYPE,"3");
                break;
            }
        }
        return ajaxResult;
    }

    /**
     * 显示 验证码
     * @param request
     * @param response
     * @param vl
     * @param w
     * @param h
     */
    @GetMapping("/checkCode")
    public void generateCpacha(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value="vl",defaultValue="4",required=false) Integer vl,
                               @RequestParam(value="w",defaultValue="110",required=false) Integer w,
                               @RequestParam(value="h",defaultValue="39",required=false) Integer h){
        CpachaUtil cpachaUtil = new CpachaUtil(vl, w, h);
        String generatorVCode = cpachaUtil.generatorVCode();
        request.getSession().setAttribute(Const.CODE, generatorVCode);
        BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
        try {
            ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转后台主页
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "/system/index";
    }


    /**
     * 登出
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "/login";
    }


    /**
     * 获取图片地址
     * @param sid
     * @param tid
     * @return
     */
    @RequestMapping("/getPhoto")
    @ResponseBody
    public AjaxResult getPhoto(@RequestParam(value = "sid",defaultValue = "0") Integer sid,
                               @RequestParam(value = "tid",defaultValue = "0")Integer tid){
        AjaxResult ajaxResult = new AjaxResult();
        if(sid != 0){
            Student student = studentService.findById(sid);
            ajaxResult.setImgurl(student.getPhoto());
            return ajaxResult;
        }
        if(tid!=0){
            Teacher teacher = teacherService.findById(tid);
            ajaxResult.setImgurl(teacher.getPhoto());
            return ajaxResult;
        }

        return ajaxResult;
    }


    @GetMapping("/personalView")
    public String personalView(){
        return "/system/personalView";
    }


    /**
     * 修改密码
     * @param password
     * @param newpassword
     * @param session
     * @return
     */
    @PostMapping("/editPassword")
    @ResponseBody
    public AjaxResult editPassword(String password,String newpassword,HttpSession session){
        AjaxResult ajaxResult = new AjaxResult();
        String usertype = (String) session.getAttribute(Const.USERTYPE);
        if (usertype.equals("1")){
            //管理员
            Admin admin = (Admin)session.getAttribute(Const.ADMIN);
            if(!password.equals(admin.getPassword())){
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("原密码错误");
                return ajaxResult;
            }
            admin.setPassword(newpassword);
            try{
                int count = adminService.editPswdByAdmin(admin);
                if(count > 0){
                    ajaxResult.setSuccess(true);
                    ajaxResult.setMessage("修改成功,请重新登录");
                }else{
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("修改失败");
                }
            }catch (Exception e){
                e.printStackTrace();
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("修改失败");
            }
        }
        if(usertype.equals("2")){
            //学生
            Student student = (Student)session.getAttribute(Const.STUDENT);
            if(!password.equals(student.getPassword())){
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("原密码错误");
                return ajaxResult;
            }
            student.setPassword(newpassword);
            try{
                int count = studentService.editPswdByStudent(student);
                if(count > 0){
                    ajaxResult.setSuccess(true);
                    ajaxResult.setMessage("修改成功,请重新登录");
                }else{
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("修改失败");
                }
            }catch (Exception e){
                e.printStackTrace();
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("修改失败");
            }
        }
        if(usertype.equals("3")){
            //教师
            Teacher teacher = (Teacher) session.getAttribute(Const.TEACHER);
            if(!password.equals(teacher.getPassword())){
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("原密码错误");
                return ajaxResult;
            }
            teacher.setPassword(newpassword);
            try{
                int count = teacherService.editPswdByTeacher(teacher);
                if(count > 0){
                    ajaxResult.setSuccess(true);
                    ajaxResult.setMessage("修改成功,请重新登录");
                }else{
                    ajaxResult.setSuccess(false);
                    ajaxResult.setMessage("修改失败");
                }
            }catch (Exception e){
                e.printStackTrace();
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("修改失败");
            }
        }
        return ajaxResult;
    }

}
