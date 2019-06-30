package com.wdd.studentmanager.service.Impl;

import com.wdd.studentmanager.domain.SelectedCourse;
import com.wdd.studentmanager.mapper.CourseMapper;
import com.wdd.studentmanager.mapper.SelectedCourseMapper;
import com.wdd.studentmanager.service.SelectedCourseService;
import com.wdd.studentmanager.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @Classname SelectedCourseServiceImpl
 * @Description None
 * @Date 2019/6/30 10:48
 * @Created by WDD
 */
@Service
public class SelectedCourseServiceImpl implements SelectedCourseService {

    @Autowired
    private SelectedCourseMapper selectedCourseMapper;
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public PageBean<SelectedCourse> queryPage(Map<String, Object> paramMap) {
        PageBean<SelectedCourse> pageBean = new PageBean<>((Integer) paramMap.get("pageno"),(Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex",startIndex);
        List<SelectedCourse> datas = selectedCourseMapper.queryList(paramMap);
        pageBean.setDatas(datas);

        Integer totalsize = selectedCourseMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    @Override
    @Transactional
    public int addSelectedCourse(SelectedCourse selectedCourse) {
        SelectedCourse s = selectedCourseMapper.findBySelectedCourse(selectedCourse);
        if(StringUtils.isEmpty(s)){
            int count = courseMapper.addStudentNum(selectedCourse.getCourseId());
            if(count == 1){
                selectedCourseMapper.addSelectedCourse(selectedCourse);
                return count;
            }
            if(count == 0){
                return count;
            }
        }else{
            return 2;
        }
        return 3;
    }
}
