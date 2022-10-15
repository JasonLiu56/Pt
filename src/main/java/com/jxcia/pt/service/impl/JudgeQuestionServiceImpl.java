package com.jxcia.pt.service.impl;

import com.jxcia.pt.entity.JudgeQuestion;
import com.jxcia.pt.mapper.JudgeQuestionMapper;
import com.jxcia.pt.service.JudgeQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JudgeQuestionServiceImpl implements JudgeQuestionService {

    @Autowired
    private JudgeQuestionMapper judgeQuestionMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean insert(Integer examId, String question, String answer, String analysis, Integer score) {
        JudgeQuestion judgeQuestion = new JudgeQuestion(examId, question, answer, analysis, score);
        return judgeQuestionMapper.insert(judgeQuestion);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer id) {
        return judgeQuestionMapper.isExistById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer id, Integer examId) {
        return judgeQuestionMapper.isExistByIdAndExamId(id, examId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer examId, String question) {
        return judgeQuestionMapper.isExistByExamIdAndQuestion(examId, question);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer examId, Integer id, String question) {
        return judgeQuestionMapper.isExistByNonIdAndExamIdAndQuestion(examId, id, question);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isEmpty(Integer examId) {
        return !judgeQuestionMapper.isExistByExamId(examId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean delete(Integer id) {
        return judgeQuestionMapper.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean update(Integer id, Integer examId, String question, String answer, String analysis, Integer score) {
        return judgeQuestionMapper.update(id, question, examId, answer, analysis, score);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public JudgeQuestion getById(Integer id) {
        return judgeQuestionMapper.getById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<JudgeQuestion> getAll(Integer examId, String question) {
        return judgeQuestionMapper.getAll(examId, question);
    }

}
