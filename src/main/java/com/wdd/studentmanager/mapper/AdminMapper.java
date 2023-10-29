package com.wdd.studentmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wdd.studentmanager.domain.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Classname UserMapper
 * @Description None
 * @Date 2019/6/24 20:09
 * @Created by WDD
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    int editPswdByAdmin(Admin admin);
}
