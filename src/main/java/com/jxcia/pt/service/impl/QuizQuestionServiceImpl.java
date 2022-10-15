package com.jxcia.pt.service.impl;

import com.jxcia.pt.dto.vo.QuizFillQuestionVo;
import com.jxcia.pt.dto.vo.QuizJudgeQuestionVo;
import com.jxcia.pt.dto.vo.QuizSelectQuestionVo;
import com.jxcia.pt.entity.FillQuestion;
import com.jxcia.pt.entity.JudgeQuestion;
import com.jxcia.pt.entity.QuizQuestion;
import com.jxcia.pt.entity.SelectQuestion;
import com.jxcia.pt.mapper.*;
import com.jxcia.pt.service.QuizQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Transactional
public class QuizQuestionServiceImpl implements QuizQuestionService {

    @Autowired
    private QuizQuestionMapper quizQuestionMapper;

    @Autowired
    private QuizMapper quizMapper;

    @Autowired
    private FillQuestionMapper fillQuestionMapper;

    @Autowired
    private SelectQuestionMapper selectQuestionMapper;

    @Autowired
    private JudgeQuestionMapper judgeQuestionMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean insert(List<QuizQuestion> quizQuestions) {
        AtomicReference<Integer> totalScore = new AtomicReference<>(0);
        // 根据答案获取得分
        quizQuestions.forEach(quizQuestion -> {
            Integer questionId = quizQuestion.getQuestionId();

            // 如果是填空题
            if (quizQuestion.getQuestionType().equals(QuizQuestion.fillQuestion)) {
                FillQuestion fillQuestion = fillQuestionMapper.getById(questionId);
                if (fillQuestion.getAnswer().equals(quizQuestion.getAnswer())) {
                    quizQuestion.setScore(fillQuestion.getScore());
                    totalScore.updateAndGet(v -> v + fillQuestion.getScore());
                } else {
                    quizQuestion.setScore(0);
                }
            }

            // 如果是判断题
            if (quizQuestion.getQuestionType().equals(QuizQuestion.judgeQuestion)) {
                JudgeQuestion judgeQuestion = judgeQuestionMapper.getById(questionId);
                if (judgeQuestion.getAnswer().equals(quizQuestion.getAnswer())) {
                    quizQuestion.setScore(judgeQuestion.getScore());
                    totalScore.updateAndGet(v -> v + judgeQuestion.getScore());
                } else {
                    quizQuestion.setScore(0);
                }
            }

            // 如果是选择题题
            if (quizQuestion.getQuestionType().equals(QuizQuestion.selectQuestion)) {
                SelectQuestion selectQuestion = selectQuestionMapper.getById(questionId);
                if (selectQuestion.getAnswer().equals(quizQuestion.getAnswer())) {
                    quizQuestion.setScore(selectQuestion.getScore());
                    totalScore.updateAndGet(v -> v + selectQuestion.getScore());
                } else {
                    quizQuestion.setScore(0);
                }
            }
        });

        // 插入测验题目
        Boolean flag = quizQuestionMapper.insert(quizQuestions);
        // 插入总分
        flag = (quizMapper.update(quizQuestions.get(0).getQuizId(), totalScore.get()) && flag);

        return flag;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isEmpty(Integer examId) {
        return quizQuestionMapper.isExistByExamId(examId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExistByQuizId(Integer quizId) {
        return quizQuestionMapper.isExistByQuizId(quizId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExistByFillQuestionId(Integer fillQuestionId) {
        return quizQuestionMapper.isExistByQuestionId(fillQuestionId, QuizQuestion.fillQuestion);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExistByJudgeQuestionId(Integer judgeQuestionId) {
        return quizQuestionMapper.isExistByQuestionId(judgeQuestionId, QuizQuestion.judgeQuestion);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExistBySelectQuestionId(Integer selectQuestionId) {
        return quizQuestionMapper.isExistByQuestionId(selectQuestionId, QuizQuestion.selectQuestion);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<QuizFillQuestionVo> getAllFillQuestion(Integer quizId) {
        return quizQuestionMapper.getAllFillQuestion(quizId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<QuizJudgeQuestionVo> getAllJudgeQuestion(Integer quizId) {
        return quizQuestionMapper.getAllJudgeQuestion(quizId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<QuizSelectQuestionVo> getAllSelectQuestion(Integer quizId) {
        return quizQuestionMapper.getAllSelectQuestion(quizId);
    }

}
