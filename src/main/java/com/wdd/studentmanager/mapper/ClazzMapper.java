package com.wdd.studentmanager.mapper;

import com.wdd.studentmanager.domain.Clazz;

import java.util.List;
import java.util.Map;

/**
 * @Classname ClazzMapper
 * @Description None
 * @Date 2019/6/24 20:09
 * @Created by WDD
 */
public interface ClazzMapper {
    List<Clazz> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

    int addClazz(Clazz clazz);

    int deleteClazz(List<Integer> ids);

    int editClazz(Clazz clazz);

    Clazz findByName(String clazzName);
}
