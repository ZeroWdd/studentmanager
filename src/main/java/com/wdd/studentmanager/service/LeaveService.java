package com.wdd.studentmanager.service;

import com.wdd.studentmanager.domain.Leave;
import com.wdd.studentmanager.util.PageBean;

import java.util.Map;

/**
 * @Classname LeaveService
 * @Description None
 * @Date 2019/7/2 15:54
 * @Created by WDD
 */
public interface LeaveService {
    PageBean<Leave> queryPage(Map<String, Object> paramMap);

    int addLeave(Leave leave);

    int editLeave(Leave leave);

    int checkLeave(Leave leave);

    int deleteLeave(Integer id);
}
