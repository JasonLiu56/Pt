package com.jxcia.pt.controller;

import com.jxcia.pt.common.Result;
import com.jxcia.pt.dto.req.QuizQuestionBatchInsertReq;
import com.jxcia.pt.dto.req.QuizQuestionGetByQuizIdReq;
import com.jxcia.pt.dto.req.QuizQuestionInsertReq;
import com.jxcia.pt.dto.vo.QuizFillQuestionVo;
import com.jxcia.pt.dto.vo.QuizJudgeQuestionVo;
import com.jxcia.pt.dto.vo.QuizQuestionVo;
import com.jxcia.pt.dto.vo.QuizSelectQuestionVo;
import com.jxcia.pt.entity.QuizQuestion;
import com.jxcia.pt.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/quiz/question")
@Slf4j
@Api(tags = "测验题目模块", value = "测验题目模块", description="测验题目模块接口")
public class QuizQuestionController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private ExamService examService;

    @Autowired
    private FillQuestionService fillQuestionService;

    @Autowired
    private JudgeQuestionService judgeQuestionService;

    @Autowired
    private SelectQuestionService selectQuestionService;

    @Autowired
    private QuizQuestionService quizQuestionService;

    @ApiOperation(value = "新增测验题目")
    @PostMapping("/insert")
    public Result insert(@RequestBody QuizQuestionBatchInsertReq params) throws Exception {
        // 获取uid
//        Integer uid = Integer.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
        Integer uid = params.getUid();

        for (QuizQuestionInsertReq quizQuestionInsertReq : params.getQuizQuestionInsertReqs()) {
            // 参数校验
            if (ObjectUtils.isEmpty(quizQuestionInsertReq.getExamId()) || ObjectUtils.isEmpty(quizQuestionInsertReq.getQuizId()) ||
                    ObjectUtils.isEmpty(quizQuestionInsertReq.getQuestionId()) || StringUtils.isEmpty(quizQuestionInsertReq.getAnswer())) {
                log.error("新增测验题目参数为空 examId:{} quizId:{} questionId:{} answer:{}", quizQuestionInsertReq.getExamId(), quizQuestionInsertReq.getQuizId(),
                        quizQuestionInsertReq.getQuestionId(), quizQuestionInsertReq.getAnswer());
                return Result.fail("新增测验题目参数为空", null);
            }

            // 检查examId是否存在
            if (!examService.isExist(quizQuestionInsertReq.getExamId())) {
                log.error("新增测验题目试卷不存在 examId:{}", quizQuestionInsertReq.getExamId());
                return Result.fail("新增测验题目试卷不存在", null);
            }

            // 检查quizId是否存在
            if (!quizService.isExist(quizQuestionInsertReq.getQuizId(), uid)) {
                log.error("新增测验题目测验不存在 quizId:{} uid:{}", quizQuestionInsertReq.getQuizId(), uid);
                return Result.fail("新增测验题目测验不存在", null);
            }

            // 检查questionType
            if (!(quizQuestionInsertReq.getQuestionType().equals(QuizQuestion.fillQuestion) || quizQuestionInsertReq.getQuestionType().equals(QuizQuestion.judgeQuestion)
                    || quizQuestionInsertReq.getQuestionType().equals(QuizQuestion.selectQuestion))) {
                log.error("新增测验题目questionType出错 questionType:{}", quizQuestionInsertReq.getQuestionType());
                return Result.fail("新增测验题目questionType出错", null);
            }

            // 根据questionType不同分别处理
            if (quizQuestionInsertReq.getQuestionType().equals(QuizQuestion.fillQuestion)) {
                if (!fillQuestionService.isExist(quizQuestionInsertReq.getQuestionId(), quizQuestionInsertReq.getExamId())) {
                    log.error("新增测验问题不存在 questionId:{} examId:{}", quizQuestionInsertReq.getQuestionId(), quizQuestionInsertReq.getExamId());
                    return Result.fail("新增测验问题不存在", null);
                }
            } else if(quizQuestionInsertReq.getQuestionType().equals(QuizQuestion.judgeQuestion)) {
                if (!judgeQuestionService.isExist(quizQuestionInsertReq.getQuestionId(), quizQuestionInsertReq.getExamId())) {
                    log.error("新增测验问题不存在 questionId:{} examId:{}", quizQuestionInsertReq.getQuestionId(), quizQuestionInsertReq.getExamId());
                    return Result.fail("新增测验问题不存在", null);
                }
            } else {
                if (!selectQuestionService.isExist(quizQuestionInsertReq.getQuestionId(), quizQuestionInsertReq.getExamId())) {
                    log.error("新增测验问题不存在 questionId:{} examId:{}", quizQuestionInsertReq.getQuestionId(), quizQuestionInsertReq.getExamId());
                    return Result.fail("新增测验问题不存在", null);
                }
            }
        }

        // 新增测验题目
        List<QuizQuestion> quizQuestions = params.getQuizQuestionInsertReqs().stream().map(quizQuestion ->
                new QuizQuestion(quizQuestion.getExamId(), quizQuestion.getQuizId(), quizQuestion.getQuestionId(), quizQuestion.getAnswer(), quizQuestion.getQuestionType())).collect(Collectors.toList());
        Boolean flag = quizQuestionService.insert(quizQuestions);

        if (flag) {
            log.info("新增测验题目成功: {}", params);
            return Result.succ("新增测验题目成功", null);
        } else {
            log.error("新增测验失败: {}", params);
            return Result.fail("新增测验题目成功", null);
        }
    }

    @ApiOperation(value = "根据quizId获取测验题目")
    @PostMapping("/getByQuizId")
    public Result getByQuizId(@RequestBody QuizQuestionGetByQuizIdReq params) throws Exception {
        // 获取uid
//        Integer uid = Integer.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
        Integer uid = params.getUid();

        if (ObjectUtils.isEmpty(params.getQuizId())) {
            log.error("根据quizId获取测验题目参数为空 quizId:{}", params.getQuizId());
            return Result.fail("根据quizId获取测验题目参数为空", null);
        }

        // 根据quizId和uid判断是否存在
        if (!quizService.isExist(params.getQuizId(), uid)) {
            log.error("用户没有此测验 quizId:{} uid:{}", params.getQuizId(), uid);
            return Result.fail("用户没有此测验");
        }

        // 通过quizId获取QuizFillQuestion
        List<QuizFillQuestionVo> fillQuestionsVo = quizQuestionService.getAllFillQuestion(params.getQuizId());
        // 通过quizId获取QuizQuestion
        List<QuizJudgeQuestionVo> judgeQuestionsVo = quizQuestionService.getAllJudgeQuestion(params.getQuizId());
        // 通过quizId获取QuizQuestion
        List<QuizSelectQuestionVo> selectQuestionsVo = quizQuestionService.getAllSelectQuestion(params.getQuizId());
        log.info("根据quizId获取测验题目 fillQuestions:{} judgeQuestions:{} selectQuestions:{}", fillQuestionsVo, judgeQuestionsVo, selectQuestionsVo);
        QuizQuestionVo quizQuestionVo = new QuizQuestionVo(fillQuestionsVo, judgeQuestionsVo, selectQuestionsVo);

        return Result.succ(quizQuestionVo);
    }

}
