package com.jxcia.pt.controller;

import com.jxcia.pt.common.Result;
import com.jxcia.pt.dto.req.*;
import com.jxcia.pt.entity.FillQuestion;
import com.jxcia.pt.service.ExamService;
import com.jxcia.pt.service.FillQuestionService;
import com.jxcia.pt.service.QuizQuestionService;
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
@RequestMapping("/question/fill")
@Slf4j
@Api(tags = "填空题模块", value = "填空题模块", description="填空题模块接口")
public class FillQuestionController {

    @Autowired
    private FillQuestionService fillQuestionService;

    @Autowired
    private ExamService examService;

    @Autowired
    private QuizQuestionService quizQuestionService;

    @ApiOperation(value = "新增填空题")
    @PostMapping("/insert")
    public Result insert(@RequestBody FillQuestionInsertReq fillQuestionInsertReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(fillQuestionInsertReq.getExamId()) || ObjectUtils.isEmpty(fillQuestionInsertReq.getScore()) ||
        StringUtils.isEmpty(fillQuestionInsertReq.getQuestion()) || StringUtils.isEmpty(fillQuestionInsertReq.getAnswer()) ||
        StringUtils.isEmpty(fillQuestionInsertReq.getAnalysis())) {
            log.error("新增填空题参数为空 examId:{} score:{} question:{}, answer:{} analysis:{}", fillQuestionInsertReq.getExamId(),
                    fillQuestionInsertReq.getScore(), fillQuestionInsertReq.getQuestion(), fillQuestionInsertReq.getAnswer(),
                    fillQuestionInsertReq.getAnalysis());
            return Result.fail("新增填空题参数为空", null);
        }

        // 校验分数
        if (fillQuestionInsertReq.getScore() <= 0 || fillQuestionInsertReq.getScore() >= 100) {
            log.error("新增填空题分数不合理 score:{}", fillQuestionInsertReq.getScore());
            return Result.fail("新增填空题分数不合理", null);
        }

        // 根据examId检查试卷是否存在
        if (!examService.isExist(fillQuestionInsertReq.getExamId())) {
            log.error("新增填空题试卷不存在 examId:{}", fillQuestionInsertReq.getExamId());
            return Result.fail("新增填空题试卷不存在", null);
        }

        // 根据examId和question校验是否存在同名的question
        if (fillQuestionService.isExist(fillQuestionInsertReq.getExamId(), fillQuestionInsertReq.getQuestion())) {
            log.error("新增填空题题目已存在 examId:{} question:{}", fillQuestionInsertReq.getExamId(), fillQuestionInsertReq.getQuestion());
            return Result.fail("新增填空题题目已存在", null);
        }

        // 新增填空题
        Boolean flag = fillQuestionService.insert(fillQuestionInsertReq.getExamId(), fillQuestionInsertReq.getQuestion(), fillQuestionInsertReq.getAnswer(),
                fillQuestionInsertReq.getAnalysis(), fillQuestionInsertReq.getScore());

        if (flag) {
            log.info("新增填空题成功: {}", fillQuestionInsertReq);
            return Result.succ("新增填空题成功", null);
        } else {
            log.error("新增填空题失败: {}", fillQuestionInsertReq);
            return Result.fail("新增填空题失败", null);
        }
    }

    @ApiOperation(value = "删除填空题")
    @PostMapping("/delete")
    public Result delete(@RequestBody FillQuestionDeleteReq fillQuestionDeleteReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(fillQuestionDeleteReq.getId())) {
            log.error("删除填空题参数为空 id:{}", fillQuestionDeleteReq.getId());
            return Result.fail("删除填空题参数为空",null);
        }

        // 查看待删除的填空题是否存在
        if (!fillQuestionService.isExist(fillQuestionDeleteReq.getId())) {
            log.error("待删除填空题不存在 id:{}", fillQuestionDeleteReq.getId());
            return Result.fail("待删除填空题不存在", null);
        }

        // 查看待删除的题目是否存在测验题目
        if (quizQuestionService.isExistByFillQuestionId(fillQuestionDeleteReq.getId())) {
            log.error("待删除填空题还存在测验题目 id:{}", fillQuestionDeleteReq.getId());
            return Result.fail("待删除填空题还存在测验题目", null);
        }

        // 删除填空题
        Boolean flag = fillQuestionService.delete(fillQuestionDeleteReq.getId());

        if (flag) {
            log.info("删除填空题成功: {}", fillQuestionDeleteReq);
            return Result.succ("删除填空题成功", null);
        } else {
            log.error("删除填空题失败: {}", fillQuestionDeleteReq);
            return Result.fail("删除填空题失败", null);
        }
    }

    @ApiOperation(value = "更新填空题")
    @PostMapping("/update")
    public Result update(@RequestBody FillQuestionUpdateReq fillQuestionUpdateReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(fillQuestionUpdateReq.getId()) || ObjectUtils.isEmpty(fillQuestionUpdateReq.getExamId()) ||
                ObjectUtils.isEmpty(fillQuestionUpdateReq.getScore()) || StringUtils.isEmpty(fillQuestionUpdateReq.getQuestion()) ||
                StringUtils.isEmpty(fillQuestionUpdateReq.getAnswer()) || StringUtils.isEmpty(fillQuestionUpdateReq.getAnalysis())) {
            log.error("更新填空题参数为空 id:{} examId:{} score:{} question:{}, answer:{} analysis:{}", fillQuestionUpdateReq.getId(),
                    fillQuestionUpdateReq.getExamId(), fillQuestionUpdateReq.getScore(), fillQuestionUpdateReq.getQuestion(),
                    fillQuestionUpdateReq.getAnswer(), fillQuestionUpdateReq.getAnalysis());
            return Result.fail("更新填空题参数为空", null);
        }

        // 校验分数
        if (fillQuestionUpdateReq.getScore() <= 0 || fillQuestionUpdateReq.getScore() >= 100) {
            log.error("更新填空题分数不合理 score:{}", fillQuestionUpdateReq.getScore());
            return Result.fail("更新填空题分数不合理", null);
        }

        // 通过id检查待更新的填空题是否存在
        if (!fillQuestionService.isExist(fillQuestionUpdateReq.getId())) {
            log.error("待更新填空题不存在 id:{}", fillQuestionUpdateReq.getId());
            return Result.fail("待更新的填空题不存在", null);
        }

        // 根据examId检查试卷是否存在
        if (!examService.isExist(fillQuestionUpdateReq.getExamId())) {
            log.error("待更新填空题试卷不存在 examId:{}", fillQuestionUpdateReq.getExamId());
            return Result.fail("待更新填空题试卷不存在", null);
        }

        // 检查统一试卷下是否存在同名的填空题(除开自己)
        if (fillQuestionService.isExist(fillQuestionUpdateReq.getId(), fillQuestionUpdateReq.getExamId(), fillQuestionUpdateReq.getQuestion())) {
            log.error("待更新填空题问题已存在 id:{} examId:{} question:{}", fillQuestionUpdateReq.getId(), fillQuestionUpdateReq.getExamId(), fillQuestionUpdateReq.getQuestion());
            return Result.fail("待更新填空题问题已存在", null);
        }

        // 更新填空题
        Boolean flag = fillQuestionService.update(fillQuestionUpdateReq.getId(), fillQuestionUpdateReq.getExamId(),
                fillQuestionUpdateReq.getQuestion(), fillQuestionUpdateReq.getAnswer(), fillQuestionUpdateReq.getAnalysis(),
                fillQuestionUpdateReq.getScore());

        if (flag) {
            log.info("更新填空题成功: {}", fillQuestionUpdateReq);
            return Result.succ("更新填空题成功", null);
        } else {
            log.error("更新填空题失败: {}", fillQuestionUpdateReq);
            return Result.fail("更新填空题失败", null);
        }
    }

    @ApiOperation(value = "通过id获取填空题")
    @PostMapping("/getById")
    public Result getById(@RequestBody FillQuestionGetByIdReq fillQuestionGetByIdReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(fillQuestionGetByIdReq.getId())) {
            log.error("通过id获取填空题参数为空 id:{}", fillQuestionGetByIdReq.getId());
            return Result.fail("通过id获取填空题参数为空", null);
        }

        // 查看待删除的填空题是否存在
        if (!fillQuestionService.isExist(fillQuestionGetByIdReq.getId())) {
            log.error("通过id获取填空题不存在 id:{}", fillQuestionGetByIdReq.getId());
            return Result.fail("通过id获取填空题不存在", null);
        }

        // 获取填空题
        FillQuestion fillQuestion = fillQuestionService.getById(fillQuestionGetByIdReq.getId());
        log.info("通过id获取填空题 fillQuestion:{}", fillQuestion);

        return Result.succ(fillQuestion);
    }

    @ApiOperation(value = "获取所有填空题")
    @PostMapping("/getAll")
    public Result getAll(@RequestBody FillQuestionGetAllReq fillQuestionGetAllReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(fillQuestionGetAllReq.getExamId())) {
            log.error("获取所有填空题参数为空 examId:{}", fillQuestionGetAllReq.getExamId());
            return Result.fail("获取所有填空题参数为空", null);
        }

        // 获取所有的填空题
        List<FillQuestion> fillQuestions = fillQuestionService.getAll(fillQuestionGetAllReq.getExamId(), fillQuestionGetAllReq.getQuestion());
        log.info("获取所有填空题 fillQuestions:{}", fillQuestions);

        return Result.succ(fillQuestions);
    }

}
