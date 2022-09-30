package com.jxcia.pt.controller;

import com.jxcia.pt.common.Result;
import com.jxcia.pt.dto.req.*;
import com.jxcia.pt.dto.vo.PageVo;
import com.jxcia.pt.entity.Exam;
import com.jxcia.pt.service.ExamService;
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
@RequestMapping("/exam")
@Slf4j
@Api(tags = "考试模块", value = "考试模块", description="考试模块接口")
public class ExamController {

    @Autowired
    private ExamService examService;

    @ApiOperation(value = "新增考试")
    @PostMapping("/insert")
    public Result insert(@RequestBody ExamInsertReq examInsertReq) throws Exception {
        // 参数检查
        if (StringUtils.isEmpty(examInsertReq.getName()) || StringUtils.isEmpty(examInsertReq.getDescription())) {
            log.error("新增考试参数为空 name:{} description:{}", examInsertReq.getName(), examInsertReq.getDescription());
            return Result.fail("新增考试参数为空", null);
        }

        // 检查是否存在同名的考试
        if (examService.isExist(examInsertReq.getName())) {
            log.error("新增考试名称已存在 name:{} description:{}", examInsertReq.getName(), examInsertReq.getDescription());
            return Result.fail("新增考试名称已存在",null);
        }

        // 新增考试
        Boolean flag = examService.insert(examInsertReq.getName(), examInsertReq.getDescription());

        if (flag) {
            log.info("新增考试成功: {}", examInsertReq);
            return Result.succ("新增考试成功", null);
        } else {
            log.error("新增考试失败: {}", examInsertReq);
            return Result.fail("新增考试失败", null);
        }
    }

    @ApiOperation(value = "删除考试")
    @PostMapping("/delete")
    public Result delete(@RequestBody ExamDeleteReq examDeleteReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(examDeleteReq.getId())) {
            log.error("删除考试参数为空 id:{}", examDeleteReq.getId());
            return Result.fail("删除考试参数为空", null);
        }

        // 判断待删除的考试是否存在
        if (!examService.isExist(examDeleteReq.getId())) {
            log.error("待删除考试不存在 id:{}", examDeleteReq.getId());
            return Result.fail("待删除考试不存在", null);
        }

        // 删除考试
        Boolean flag = examService.delete(examDeleteReq.getId());

        if (flag) {
            log.info("删除考试成功: {}", examDeleteReq);
            return Result.succ("删除考试成功", null);
        } else {
            log.error("删除考试失败: {}", examDeleteReq);
            return Result.fail("删除考试失败", null);
        }
    }


    @ApiOperation(value = "更新考试")
    @PostMapping("/update")
    public Result update(@RequestBody ExamUpdateReq examUpdateReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(examUpdateReq.getId()) || StringUtils.isEmpty(examUpdateReq.getName()) ||
                StringUtils.isEmpty(examUpdateReq.getDescription())) {
            log.error("更新考试参数为空 id:{} name:{} description:{}", examUpdateReq.getId(), examUpdateReq.getName(),
                    examUpdateReq.getDescription());
            return Result.fail("更新考试参数为空", null);
        }

        // 检查待更新的考试是否存在
        if (!examService.isExist(examUpdateReq.getId())) {
            log.error("待更新的考试不存在 id:{}", examUpdateReq.getId());
            return Result.fail("待更新的考试不存在", null);
        }

        // 检查是否存在同名的考试(除开自己)
        if (examService.isExist(examUpdateReq.getId(), examUpdateReq.getName())) {
            log.error("待更新的考试名称已存在 id:{} name:{}", examUpdateReq.getId(), examUpdateReq.getName());
            return Result.fail("待更新的考试名称已存在", null);
        }

        // 更新考试
        Boolean flag = examService.update(examUpdateReq.getId(), examUpdateReq.getName(), examUpdateReq.getDescription());

        if (flag) {
            log.info("更新考试成功: {}", examUpdateReq);
            return Result.succ("更新考试成功", null);
        } else {
            log.error("更新考试失败: {}", examUpdateReq);
            return Result.fail("更新考试失败", null);
        }
    }

    @ApiOperation(value = "通过id查询考试")
    @PostMapping("/getById")
    public Result getById(@RequestBody ExamGetByIdReq examGetByIdReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(examGetByIdReq.getId())) {
            log.error("通过id查询考试参数为空 id:{}", examGetByIdReq.getId());
            return Result.fail("通过id查询考试参数为空", null);
        }

        // 通过id查看考试是否存在
        if (!examService.isExist(examGetByIdReq.getId())) {
            log.error("待查询的考试不存在 id:{}", examGetByIdReq.getId());
            return Result.fail("待查询的考试不存在", null);
        }

        // 通过id查询考试
        Exam exam = examService.getById(examGetByIdReq.getId());
        log.info("通过id查询考试 id:{} exam:{}", examGetByIdReq.getId(), exam);

        return Result.succ(exam);
    }

    @ApiOperation(value = "分页查询考试")
    @PostMapping("/getByPage")
    public Result getByPage(@RequestBody ExamGetByPageReq examGetByPageReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(examGetByPageReq.getPageNum()) || ObjectUtils.isEmpty(examGetByPageReq.getPageSize())) {
            log.error("分页查询考试参数为空 pageNum:{} pageSize:{}", examGetByPageReq.getPageNum(), examGetByPageReq.getPageSize());
            return Result.fail("分页查询考试参数为空", null);
        }

        // 分页查询
        List<Exam> exams = examService.getByPage(examGetByPageReq.getPageNum(), examGetByPageReq.getPageSize(),
                examGetByPageReq.getName(), examGetByPageReq.getDescription());
        // 分页统计总数
        Integer total = examService.countByPage(examGetByPageReq.getName(), examGetByPageReq.getDescription());
        log.info("分页查询考试 total:{} exams:{}", total, exams);
        // 构建分页响应体
        PageVo pageVo = new PageVo(total, exams);

        return Result.succ(pageVo);
    }

}
