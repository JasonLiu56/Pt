package com.jxcia.pt.service.impl;

import com.jxcia.pt.entity.FillQuestion;
import com.jxcia.pt.mapper.FillQuestionMapper;
import com.jxcia.pt.service.FillQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FillQuestionServiceImpl implements FillQuestionService {

    @Autowired
    private FillQuestionMapper fillQuestionMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean insert(Integer examId, String question, String answer, String analysis, Integer score) {
        FillQuestion fillQuestion = new FillQuestion(examId, question, answer, analysis, score);
        return fillQuestionMapper.insert(fillQuestion);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer id) {
        return fillQuestionMapper.isExistById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer id, Integer examId) {
        return fillQuestionMapper.isExistByIdAndExamId(id, examId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer examId, String question) {
        return fillQuestionMapper.isExistByExamIdAndQuestion(examId, question);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer id, Integer examId, String question) {
        return fillQuestionMapper.isExistByNonIdAndExamIdQuestion(id, examId, question);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isEmpty(Integer examId) {
        return !fillQuestionMapper.isExistByExamId(examId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean delete(Integer id) {
        return fillQuestionMapper.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean update(Integer id, Integer examId, String question, String answer, String analysis, Integer score) {
        return fillQuestionMapper.update(id, examId, question, answer, analysis, score);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public FillQuestion getById(Integer id) {
        return fillQuestionMapper.getById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<FillQuestion> getAll(Integer examId, String question) {
        return fillQuestionMapper.getAll(examId, question);
    }

}
