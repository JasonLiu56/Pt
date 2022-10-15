package com.jxcia.pt.service.impl;

import com.jxcia.pt.entity.SelectQuestion;
import com.jxcia.pt.mapper.SelectQuestionMapper;
import com.jxcia.pt.service.SelectQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SelectQuestionServiceImpl implements SelectQuestionService {

    @Autowired
    private SelectQuestionMapper selectQuestionMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean insert(Integer examId, String question, String answerA, String answerB, String answerC, String answerD, String answer, String analysis, Integer score) {
        SelectQuestion selectQuestion = new SelectQuestion(examId, question, answerA, answerB, answerC, answerD, answer, analysis, score);
        return selectQuestionMapper.insert(selectQuestion);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer id) {
        return selectQuestionMapper.isExistById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer id, Integer examId) {
        return selectQuestionMapper.isExistByIdAndExamId(id, examId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer examId, String question) {
        return selectQuestionMapper.isExistByExamIdAndQuestion(examId, question);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer examId, Integer id, String question) {
        return selectQuestionMapper.isExistByNonIdAndExamIdQuestion(examId, id, question);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isEmpty(Integer examId) {
        return !selectQuestionMapper.isExistByExamId(examId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean delete(Integer id) {
        return selectQuestionMapper.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean update(Integer id, Integer examId, String question, String answerA, String answerB, String answerC, String answerD, String answer, String analysis, Integer score) {
        return selectQuestionMapper.update(id, examId, question, answerA, answerB, answerC, answerD, answer, analysis, score);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public SelectQuestion getById(Integer id) {
        return selectQuestionMapper.getById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<SelectQuestion> getAll(Integer examId, String question) {
        return selectQuestionMapper.getAll(examId, question);
    }

}
