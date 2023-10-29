package com.wdd.studentmanager.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wdd.studentmanager.domain.Attendance;
import com.wdd.studentmanager.mapper.AttendanceMapper;
import com.wdd.studentmanager.service.AttendanceService;
import com.wdd.studentmanager.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Classname AttendanceServiceImpl
 * @Description None
 * @Date 2019/7/1 15:47
 * @Created by WDD
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public PageBean<Attendance> queryPage(Map<String, Object> paramMap) {
        Integer pagesize = (Integer) paramMap.get("pagesize");
        PageBean<Attendance> pageBean = new PageBean<>((Integer) paramMap.get("pageno"), pagesize);
        Integer startIndex = pageBean.getStartIndex();

        LambdaQueryWrapper<Attendance> queryWrapper = new LambdaQueryWrapper<>();
        String courseid = (String) paramMap.get("courseid");
        String studentid = (String) paramMap.get("studentid");
        String type = (String) paramMap.get("type");
        String date = (String) paramMap.get("date");
        queryWrapper
                .eq(courseid != null, Attendance::getCourseId, courseid)
                .eq(studentid != null, Attendance::getStudentId, studentid)
                .eq(type != null, Attendance::getType, type)
                .eq(date != null, Attendance::getDate, date)
                .last("limit " + startIndex + ", " + pagesize);
        List<Attendance> attendances = attendanceMapper.selectList(queryWrapper);
        pageBean.setDatas(attendances);
        pageBean.setTotalsize(attendances.size());
        return pageBean;
    }

    @Override
    public boolean isAttendance(Attendance attendance) {
        Attendance at = attendanceMapper.isAttendance(attendance);
        if (at != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int addAtendance(Attendance attendance) {
        return attendanceMapper.addAtendance(attendance);
    }

    @Override
    public int deleteAttendance(Integer id) {
        return attendanceMapper.deleteAttendance(id);
    }
}
