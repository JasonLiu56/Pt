package com.jxcia.pt.service.impl;

import com.github.pagehelper.PageHelper;
import com.jxcia.pt.entity.Course;
import com.jxcia.pt.mapper.CourseMapper;
import com.jxcia.pt.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean insert(String name, Integer categoryId) {
        Course course = new Course(name, categoryId);
        return courseMapper.insert(course);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer id) {
        return courseMapper.isExistById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(String name) {
        return courseMapper.isExistByName(name);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer id, String name) {
        return courseMapper.isExistByNonIdAndName(id, name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean delete(Integer id) {
        return courseMapper.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean update(Integer id, String name, Integer categoryId) {
        return courseMapper.update(id, name, categoryId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Course getById(Integer id) {
        return courseMapper.getById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Course> getByPage(Integer pageNum, Integer pageSize, String name, Integer categoryId) {
        PageHelper.startPage(pageNum - 1, pageSize);
        return courseMapper.getByPage(name, categoryId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer countByPage(String name, Integer categoryId) {
        return courseMapper.countByPage(name, categoryId);
    }

}
