package com.jxcia.pt.controller;

import com.jxcia.pt.common.Result;
import com.jxcia.pt.dto.req.*;
import com.jxcia.pt.entity.SelectQuestion;
import com.jxcia.pt.service.ExamService;
import com.jxcia.pt.service.QuizQuestionService;
import com.jxcia.pt.service.SelectQuestionService;
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

@RestController
@RequestMapping("/question/select")
@Slf4j
@Api(tags = "选择题模块", value = "选择题模块", description="选择题模块接口")
public class SelectQuestionController {

    @Autowired
    private SelectQuestionService selectQuestionService;

    @Autowired
    private ExamService examService;

    @Autowired
    private QuizQuestionService quizQuestionService;

    @ApiOperation(value = "新增选择题")
    @PostMapping("/insert")
    public Result insert(@RequestBody SelectQuestionInsertReq params) throws Exception {
        // 参数为空校验
        if (ObjectUtils.isEmpty(params.getExamId()) || StringUtils.isEmpty(params.getQuestion()) || StringUtils.isEmpty(params.getAnswerA()) ||
        StringUtils.isEmpty(params.getAnswerB()) || StringUtils.isEmpty(params.getAnswerC()) || StringUtils.isEmpty(params.getAnswerD()) ||
        StringUtils.isEmpty(params.getAnswer()) || StringUtils.isEmpty(params.getAnalysis()) || ObjectUtils.isEmpty(params.getScore())) {
            log.error("新增选择题参数为空 examId:{} question:{} answerA:{} answerB:{} answerC:{} answerD:{} answer:{} analysis:{} score:{}", params.getExamId(),
                    params.getQuestion(), params.getAnswerA(), params.getAnswerB(), params.getAnswerC(), params.getAnswerD(), params.getAnswer(),
                    params.getAnalysis(), params.getScore());
            return Result.fail("新增选择题参数为空", null);
        }

        // 检查分数
        if (params.getScore() <= 0 || params.getScore() >= 100) {
            log.error("新增选择题分数不合理 score:{}", params.getScore());
            return Result.fail("新增选择题分数不合理");
        }

        // 根据examId查看是否存在
        if (!examService.isExist(params.getExamId())) {
            log.error("新增选择题在同套试卷下已存在 examId:{}", params.getExamId());
            return Result.fail("新增选择题在同套试卷下已存在", null);
        }

        // 查看同一套试卷下是否有存在相同的选择题
        if (selectQuestionService.isExist(params.getExamId(), params.getQuestion())) {
            log.error("同套试卷下选择题已经存在 examId:{} question:{}", params.getExamId(), params.getQuestion());
            return Result.fail("同套试卷下选择题已经存在", null);
        }

        // 新增选择题
        Boolean flag = selectQuestionService.insert(params.getExamId(), params.getQuestion(), params.getAnswerA(), params.getAnswerB(),
                params.getAnswerC(), params.getAnswerD(), params.getAnswer(), params.getAnalysis(), params.getScore());

        if (flag) {
            log.info("新增选择题成功 params:{}", params);
            return Result.succ("新增选择题成功", null);
        } else {
            log.error("新增选择题失败 params:{}", params);
            return Result.succ("新增选择题失败", null);
        }
    }

    @ApiOperation(value = "删除选择题")
    @PostMapping("/delete")
    public Result delete(@RequestBody SelectQuestionDeleteReq params) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(params.getId())) {
            log.error("删除选择题参数为空 id:{}", params.getId());
            return Result.fail("删除选择题参数为空");
        }

        // 查看待删除的选择题是否存在
        if (!selectQuestionService.isExist(params.getId())) {
            log.error("待删除的选择题不存在 id:{}", params.getId());
            return Result.fail("待删除的选择题不存在");
        }

        // 判断是否还存在测验题
        if (quizQuestionService.isExistBySelectQuestionId(params.getId())) {
            log.error("待删除选择题还存在测验题目 id:{}", params.getId());
            return Result.fail("待删除选择题还存在测验题目", null);
        }

        // 删除选择器
        Boolean flag = selectQuestionService.delete(params.getId());

        if (flag) {
            log.info("删除选择题成功 params:{}", params);
            return Result.succ("删除选择题成功", null);
        } else {
            log.error("删除选择题失败 params:{}", params);
            return Result.succ("删除选择题失败", null);
        }
    }

    @ApiOperation(value = "更新选择题")
    @PostMapping("/update")
    public Result update(@RequestBody SelectQuestionUpdateReq params) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(params.getId()) || ObjectUtils.isEmpty(params.getExamId()) || StringUtils.isEmpty(params.getQuestion()) ||
        StringUtils.isEmpty(params.getAnswerA()) || StringUtils.isEmpty(params.getAnswerB()) || StringUtils.isEmpty(params.getAnswerC()) ||
                StringUtils.isEmpty(params.getAnswerD()) || StringUtils.isEmpty(params.getAnswer()) || StringUtils.isEmpty(params.getAnalysis()) ||
        ObjectUtils.isEmpty(params.getScore())) {
            log.error("更新选择题参数为空 id:{} examId:{} question:{} answerA:{} answerB:{} answerC:{} answerD:{} answer:{} analysis:{} score:{}",
                    params.getId(), params.getExamId(), params.getQuestion(), params.getAnswerA(), params.getAnswerB(),
                    params.getAnswerC(), params.getAnswerD(), params.getAnswer(), params.getAnalysis(), params.getScore());
            return Result.fail("更新选择题参数为空");
        }

        // 检查分数
        if (params.getScore() <= 0 || params.getScore() >= 100) {
            log.error("更新选择题分数不合理 score:{}", params.getScore());
            return Result.fail("更新选择题分数不合理");
        }

        // 查看待删除的选择题是否存在
        if (!selectQuestionService.isExist(params.getId())) {
            log.error("待更新的选择题不存在 id:{}", params.getId());
            return Result.fail("待更新的选择题不存在");
        }

        // 根据examId查看是否存在
        if (!examService.isExist(params.getExamId())) {
            log.error("更新选择题试卷不存在 examId:{}", params.getExamId());
            return Result.fail("更新选择题不试卷存在", null);
        }

        // 查看同一套试卷下是否有存在相同的选择题(除开自己)
        if (selectQuestionService.isExist(params.getExamId(), params.getId(), params.getQuestion())) {
            log.error("同套试卷下选择题已经存在 examId:{} id:{} question:{}", params.getExamId(), params.getId(), params.getQuestion());
            return Result.fail("同套试卷下选择题已经存在", null);
        }

        // 更新选择题
        Boolean flag = selectQuestionService.update(params.getId(), params.getExamId(), params.getQuestion(), params.getAnswerA(), params.getAnswerB(),
                params.getAnswerC(), params.getAnswerD(), params.getAnswer(), params.getAnalysis(), params.getScore());

        if (flag) {
            log.info("更新选择题成功 params:{}", params);
            return Result.succ("更新选择题成功", null);
        } else {
            log.error("更新选择题失败 params:{}", params);
            return Result.succ("更新选择题失败", null);
        }
    }

    @ApiOperation(value = "根据id获取选择题")
    @PostMapping("/getById")
    public Result getById(@RequestBody SelectQuestionGetByIdReq params) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(params.getId())) {
            log.error("根据id获取选择题参数为空 id:{}", params.getId());
            return Result.fail("根据id获取选择题参数为空");
        }

        // 根据id判断是否存在
        if (!selectQuestionService.isExist(params.getId())) {
            log.error("根据id获取选择题不存在 id:{}", params.getId());
            return Result.fail("根据id获取选择题不存在");
        }

        // 根据id获取选择题
        SelectQuestion selectQuestion = selectQuestionService.getById(params.getId());
        log.info("根据id获取选择题 selectQuestion:{}", selectQuestion);

        return Result.succ(selectQuestion);
    }

    @ApiOperation(value = "获取所有选择题")
    @PostMapping("/getAll")
    public Result getAll(@RequestBody SelectQuestionGetAllReq params) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(params.getExamId())) {
            log.error("获取所有选择题参数为空 examId:{}", params.getExamId());
            return Result.fail("获取所有选择题参数为空");
        }

        // 获取所有选择题
        List<SelectQuestion> selectQuestions = selectQuestionService.getAll(params.getExamId(), params.getQuestion());
        log.info("获取所有选择题 selectQuestion:{}", selectQuestions);

        return Result.succ(selectQuestions);
    }

}