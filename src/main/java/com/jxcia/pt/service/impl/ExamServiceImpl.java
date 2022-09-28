package com.jxcia.pt.service.impl;

import com.github.pagehelper.PageHelper;
import com.jxcia.pt.entity.Exam;
import com.jxcia.pt.mapper.ExamMapper;
import com.jxcia.pt.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean insert(String name, String description) {
        Exam exam = new Exam(name, description);
        return examMapper.insert(exam);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer id) {
        return examMapper.isExistById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(String name) {
        return examMapper.isExistByName(name);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer id, String name) {
        return examMapper.isExistByNonIdAndName(id, name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean delete(Integer id) {
        return examMapper.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean update(Integer id, String name, String description) {
        return examMapper.update(id, name, description);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Exam getById(Integer id) {
        return examMapper.getById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Exam> getByPage(Integer pageNum, Integer pageSize, String name, String description) {
        PageHelper.startPage(pageNum - 1, pageSize);
        return examMapper.getByPage(name, description);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer countByPage(String name, String description) {
        return examMapper.countByPage(name, description);
    }
}
