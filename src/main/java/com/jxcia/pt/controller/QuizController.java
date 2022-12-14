package com.jxcia.pt.controller;

import com.jxcia.pt.common.Result;
import com.jxcia.pt.dto.req.*;
import com.jxcia.pt.dto.vo.PageVo;
import com.jxcia.pt.entity.Quiz;
import com.jxcia.pt.service.ExamService;
import com.jxcia.pt.service.QuizQuestionService;
import com.jxcia.pt.service.QuizService;
import com.jxcia.pt.service.UserService;
import com.jxcia.pt.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@Slf4j
@Api(tags = "测验模块", value = "测验模块", description="测验模块接口")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private ExamService examService;

    @Autowired
    private UserService userService;

    @Autowired
    private QuizQuestionService quizQuestionService;

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "新增测验")
    @PostMapping("/insert")
    public Result insert(@RequestBody QuizInsertReq params) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(params.getExamId())) {
            log.error("新增测验参数为空 examId:{}", params.getExamId());
            return Result.fail("新增测验参数为空", null);
        }

        // 检查examId是否存在
        if (!examService.isExist(params.getExamId())) {
            log.error("新增测验试卷不存在 examId:{}", params.getExamId());
            return Result.fail("新增测验试卷不存在");
        }

        // 获取uid
        Integer uid = null;
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!StringUtils.isEmpty(userId)) {
            uid = Integer.valueOf(userId);
        }

        // 判断uid是否为空
        if (ObjectUtils.isEmpty(uid)) {
            log.error("请登录");
            return Result.fail("请登录");
        }

        // 检查uid是否存在
        if (!userService.isExist(uid)) {
            log.error("新增测验用户不存在 uid:{}", uid);
            return Result.fail("新增测验用户不存在");
        }

        // 新增测验
        Boolean flag = quizService.insert(params.getExamId(), uid);

        if (flag) {
            log.info("新增测验成功: {}", params);
            return Result.succ("新增测验成功", null);
        } else {
            log.error("新增测验失败: {}", params);
            return Result.fail("新增测验成功", null);
        }
    }

    @ApiOperation(value = "删除测验")
    @PostMapping("/delete")
    public Result delete(@RequestBody QuizDeleteReq params) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(params.getId())) {
            log.error("删除测验参数为空 id:{}", params.getId());
            return Result.fail("删除测验参数为空", null);
        }

        // 通过id检查是否存在
        if (!quizService.isExist(params.getId())) {
            log.error("待删除测验不存在 id:{}", params.getId());
            return Result.fail("待删除测验不存在");
        }

        // 通过quizId查看quizQuestion是否存在
        if (quizQuestionService.isExistByQuizId(params.getId())) {
            log.error("待删除测验还存在测验题目 quizId:{}", params.getId());
            return Result.fail("待删除测验还存在测验题目");
        }

        // 获取uid
        Integer uid = null;
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!StringUtils.isEmpty(userId)) {
            uid = Integer.valueOf(userId);
        }

        // 判断uid是否为空
        if (ObjectUtils.isEmpty(uid)) {
            log.error("请登录");
            return Result.fail("请登录");
        }

        // 查询id examId uid是否存在
        if (!quizService.isExist(params.getId(), uid)) {
            log.error("删除测验该用户不存在测验 id:{} uid:{}", params.getId(), uid);
            return Result.fail("删除测验该用户不存在测验");
        }

        // 删除测验
        Boolean flag = quizService.delete(params.getId(), uid);

        if (flag) {
            log.info("删除测验成功: {}", params);
            return Result.succ("删除测验成功", null);
        } else {
            log.error("删除测验失败: {}", params);
            return Result.fail("删除测验成功", null);
        }
    }

    @ApiOperation(value = "通过id获取测验")
    @PostMapping("/getById")
    public Result getById(@RequestBody QuizGetByIdReq params) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(params.getId())) {
            log.error("通过id获取测验为空 id:{}", params.getId());
            return Result.fail("通过id获取测验为空");
        }

        // 通过id检查是否存在
        if (!quizService.isExist(params.getId())) {
            log.error("通过id获取测验不存在 id:{}", params.getId());
            return Result.fail("通过id获取测验不存在");
        }

        // 获取uid
        Integer uid = null;
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!StringUtils.isEmpty(userId)) {
            uid = Integer.valueOf(userId);
        }

        // 判断uid是否为空
        if (ObjectUtils.isEmpty(uid)) {
            log.error("请登录");
            return Result.fail("请登录");
        }

        // 根据id uid查询是否存在
        if (!quizService.isExist(params.getId(), uid)) {
            log.error("用户没有此测验 id:{} uid:{}", params.getId(), uid);
            return Result.fail("用户没有此测验");
        }

        // 获取测验
        Quiz quiz = quizService.getById(params.getId(), uid);
        log.info("通过id获取测验 quiz:{}", quiz);

        return Result.succ(quiz);
    }

    @ApiOperation(value = "获取所有测验")
    @PostMapping("/getByPage")
    public Result getAll(@RequestBody QuizGetByPageReq params) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(params.getPageNum()) || ObjectUtils.isEmpty(params.getPageSize())) {
            log.error("获取所有测验参数为空 pageNum:{} pageSize:{}", params.getPageNum(), params.getPageSize());
            return Result.fail("获取所有测验参数为空");
        }

        // 获取uid
        Integer uid = null;
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!StringUtils.isEmpty(userId)) {
            uid = Integer.valueOf(userId);
        }

        // 判断uid是否为空
        if (ObjectUtils.isEmpty(uid)) {
            log.error("请登录");
            return Result.fail("请登录");
        }

        // 检查uid是否存在
        if (!userService.isExist(uid)) {
            log.error("获取所有测验用户不存在 uid:{}", uid);
            return Result.fail("获取所有测验用户不存在");
        }

        // 获取所有测验
        List<Quiz> quizs = quizService.getByPage(params.getPageNum(), params.getPageSize(), uid, params.getExamName());
        // 获取分页统计总数
        Integer total = quizService.countByPage(uid, params.getExamName());
        // 分页vo
        PageVo pageVo = new PageVo(total, quizs);
        log.info("获取所有测验 total:{} quizs:{}", total, quizs);

        return Result.succ(pageVo);
    }

    @ApiOperation(value = "教师获取所有测验")
    @PostMapping("/getByPageTeacher")
    public Result getAll(@RequestBody QuizGetByPageTeacherReq params) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(params.getUid())) {
            log.error("教师获取所有测验参数为空 uid:{}", params.getUid());
            return Result.fail("教师获取所有测验参数为空");
        }

        // 检查uid是否存在
        if (!userService.isExist(params.getUid())) {
            log.error("新增测验用户不存在 uid:{}", params.getUid());
            return Result.fail("新增测验用户不存在");
        }

        // 获取所有测验
        List<Quiz> quizs = quizService.getByPage(params.getPageNum(), params.getPageSize(), params.getUid(), params.getExamName());
        // 获取分页统计总数
        Integer total = quizService.countByPage(params.getUid(), params.getExamName());
        // 分页vo
        PageVo pageVo = new PageVo(total, quizs);
        log.info("获取所有测验 total:{} quizs:{}", total, quizs);

        return Result.succ(pageVo);
    }

}
