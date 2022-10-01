package com.jxcia.pt.service.impl;

import com.github.pagehelper.PageHelper;
import com.jxcia.pt.entity.Quiz;
import com.jxcia.pt.mapper.QuizMapper;
import com.jxcia.pt.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizMapper quizMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean insert(Integer examId, Integer uid) {
        Quiz quiz = new Quiz(examId, uid);
        return quizMapper.insert(quiz);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer id) {
        return quizMapper.isExistById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer id, Integer uid) {
        return quizMapper.isExistByIdAndUid(id, uid);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean delete(Integer id, Integer uid) {
        return quizMapper.delete(id, uid);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Quiz getById(Integer id, Integer uid) {
        return quizMapper.getById(id, uid);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Quiz> getByPage(Integer pageNum, Integer pageSize, Integer uid, String examName) {
        PageHelper.startPage(pageNum, pageSize);
        return quizMapper.getByPage(uid, examName);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer countByPage(Integer uid, String examName) {
        return quizMapper.countByPage(uid, examName);
    }

}
