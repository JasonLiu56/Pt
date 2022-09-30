package com.jxcia.pt.controller;

import com.jxcia.pt.common.Result;
import com.jxcia.pt.dto.req.*;
import com.jxcia.pt.entity.JudgeQuestion;
import com.jxcia.pt.service.ExamService;
import com.jxcia.pt.service.JudgeQuestionService;
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
@RequestMapping("/question/judge")
@Slf4j
@Api(tags = "判断题模块", value = "判断题模块", description="判断题模块接口")
public class JudgeQuestionController {

    @Autowired
    private JudgeQuestionService judgeQuestionService;

    @Autowired
    private ExamService examService;

    @ApiOperation(value = "新增判断题")
    @PostMapping("/insert")
    public Result insert(@RequestBody JudgeQuestionInsertReq params) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(params.getExamId()) || StringUtils.isEmpty(params.getQuestion()) ||
        StringUtils.isEmpty(params.getAnswer()) || StringUtils.isEmpty(params.getAnalysis()) ||
                ObjectUtils.isEmpty(params.getScore())) {
            log.error("新增判断题参数为空 examId:{} question:{} answer:{} analysis:{} score:{}", params.getExamId(), params.getQuestion(),
                    params.getAnswer(), params.getAnalysis(), params.getScore());
            return Result.fail("新增判断题参数为空", null);
        }

        // 判断分数是否符合逻辑
        if (params.getScore() < 0 || params.getScore() > 100) {
            log.error("新增判断题分数不合理 score:{}", params.getScore());
            return Result.fail("新增判断题分数不合理", null);
        }

        // 检查examId是否存在
        if (!examService.isExist(params.getExamId())) {
            log.error("新增判断题试卷不存在 examId:{}", params.getExamId());
            return Result.fail("新增判断题试卷不存在", null);
        }

        // 根据examId和question判断是否已经存在
        if (judgeQuestionService.isExist(params.getExamId(), params.getQuestion())) {
            log.error("同套试卷下已经存在相同的判断题 examId:{} question:{}", params.getExamId(), params.getQuestion());
            return Result.fail("同套试卷下已经存在相同的判断题", null);
        }

        // 新增判断题
        Boolean flag = judgeQuestionService.insert(params.getExamId(), params.getQuestion(), params.getAnswer(),
                params.getAnalysis(), params.getScore());

        if (flag) {
            log.info("新增判断题成功: {}", params);
            return Result.succ("新增判断题成功", null);
        } else {
            log.error("新增判断题失败: {}", params);
            return Result.fail("新增判断题失败", null);
        }
    }

    @ApiOperation(value = "删除判断题")
    @PostMapping("/delete")
    public Result delete(@RequestBody JudgeQuestionDeleteReq params) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(params.getId())) {
            log.error("删除判断题参数为空 id:{}", params.getId());
            return Result.fail("删除判断题参数为空", null);
        }

        // 查看待删除的判断题是否存在
        if (!judgeQuestionService.isExist(params.getId())) {
            log.error("待删除判断题不存在 id:{}", params.getId());
            return Result.fail("待删除判断题不存在", null);
        }

        // 删除判断题
        Boolean flag = judgeQuestionService.delete(params.getId());

        if (flag) {
            log.info("删除判断题成功: {}", params);
            return Result.succ("删除判断题成功", null);
        } else {
            log.error("删除判断题失败: {}", params);
            return Result.fail("删除判断题失败", null);
        }
    }

    @ApiOperation(value = "更新判断题")
    @PostMapping("/update")
    public Result update(@RequestBody JudgeQuestionUpdateReq params) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(params.getId()) || ObjectUtils.isEmpty(params.getExamId()) || StringUtils.isEmpty(params.getQuestion()) ||
        StringUtils.isEmpty(params.getAnswer()) || StringUtils.isEmpty(params.getAnalysis()) || ObjectUtils.isEmpty(params.getScore())) {
            log.error("更新判断题参数为空 id:{} examId:{} question:{} answer:{} analysis:{} score:{}", params.getId(), params.getExamId(), params.getQuestion(),
                    params.getAnswer(), params.getAnalysis(), params.getScore());
            return Result.fail("更新判断题参数为空", null);
        }

        // 判断分数是否符合逻辑
        if (params.getScore() < 0 || params.getScore() > 100) {
            log.error("更新判断题分数不合理 score:{}", params.getScore());
            return Result.fail("更新判断题分数不合理", null);
        }

        // 查看待更新的判断题是否存在
        if (!judgeQuestionService.isExist(params.getId())) {
            log.error("待更新判断题不存在 id:{}", params.getId());
            return Result.fail("待更新判断题不存在", null);
        }

        // 检查examId是否存在
        if (!examService.isExist(params.getExamId())) {
            log.error("更新判断题试卷不存在 examId:{}", params.getExamId());
            return Result.fail("更新判断题试卷不存在", null);
        }

        // 根据examId和question判断是否已经存在(除开自己)
        if (judgeQuestionService.isExist(params.getExamId(), params.getId(), params.getQuestion())) {
            log.error("同套试卷下已经存在相同的判断题 examId:{} question:{}", params.getExamId(), params.getQuestion());
            return Result.fail("同套试卷下已经存在相同的判断题", null);
        }

        // 更新判断题
        Boolean flag = judgeQuestionService.update(params.getId(), params.getExamId(), params.getQuestion(), params.getAnswer(),
                params.getAnalysis(), params.getScore());

        if (flag) {
            log.info("更新判断题成功: {}", params);
            return Result.succ("更新判断题成功", null);
        } else {
            log.error("更新判断题失败: {}", params);
            return Result.fail("更新判断题失败", null);
        }
    }

    @ApiOperation(value = "通过id获取判断题")
    @PostMapping("/getById")
    public Result getById(@RequestBody JudgeQuestionGetByIdReq params) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(params.getId())) {
            log.error("通过id获取判断题参数为空 id:{}", params.getId());
            return Result.fail("通过id获取判断题参数为空", null);
        }

        // 查看待更新的判断题是否存在
        if (!judgeQuestionService.isExist(params.getId())) {
            log.error("通过id获取判断题不存在 id:{}", params.getId());
            return Result.fail("通过id获取判断题不存在", null);
        }

        // 通过id获取判断题
        JudgeQuestion judgeQuestion = judgeQuestionService.getById(params.getId());
        log.info("通过id获取判断题 judgeQuestion:{}", judgeQuestion);

        return Result.succ(judgeQuestion);
    }

    @ApiOperation(value = "获取所有判断题")
    @PostMapping("/getAll")
    public Result getAll(@RequestBody JudgeQuestionGetAllReq params) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(params.getExamId())) {
            log.error("获取所有判断题参数为空 examId:{}", params.getExamId());
            return Result.fail("获取所有判断题参数为空", null);
        }

        // 获取所有判断题
        List<JudgeQuestion> judgeQuestions = judgeQuestionService.getAll(params.getExamId(), params.getQuestion());
        log.info("获取所有的判断题 judgeQuestions:{}", judgeQuestions);

        return Result.succ(judgeQuestions);
    }

}
