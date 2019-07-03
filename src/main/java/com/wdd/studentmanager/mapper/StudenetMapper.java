package com.wdd.studentmanager.mapper;

import com.wdd.studentmanager.domain.Student;

import java.util.List;
import java.util.Map;

/**
 * @Classname StudenetMapper
 * @Description None
 * @Date 2019/6/24 20:09
 * @Created by WDD
 */
public interface StudenetMapper {
    List<Student> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

    int deleteStudent(List<Integer> ids);

    int addStudent(Student student);

    Student findById(Integer sid);

    int editStudent(Student student);

    Student findByStudent(Student student);

    List<Student> isStudentByClazzId(Integer id);

    int editPswdByStudent(Student student);

    int findByName(String name);
}
