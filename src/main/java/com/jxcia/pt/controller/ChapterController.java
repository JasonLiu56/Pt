package com.jxcia.pt.controller;

import com.jxcia.pt.common.Result;
import com.jxcia.pt.dto.req.*;
import com.jxcia.pt.entity.Chapter;
import com.jxcia.pt.service.ChapterService;
import com.jxcia.pt.service.CourseService;
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
@RequestMapping("/chapter")
@Slf4j
@Api(tags = "章节模块", value = "章节模块", description="章节模块接口")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "新增章节")
    @PostMapping("/insert")
    public Result insert(@RequestBody ChapterInsertReq chapterInsertReq) throws Exception {
        // 参数检查
        if (StringUtils.isEmpty(chapterInsertReq.getName()) || ObjectUtils.isEmpty(chapterInsertReq.getCourseId())) {
            log.error("新增章节参数为空 name:{} courseId:{}", chapterInsertReq.getName(), chapterInsertReq.getCourseId());
            return Result.fail("新增章节参数为空");
        }

        // 查看同一门课程下是否已经存在相同的章节
        if (chapterService.isExist(chapterInsertReq.getCourseId(), chapterInsertReq.getName())) {
            log.error("课程下章节已经存在 name:{}, courseId:{}", chapterInsertReq.getName(), chapterInsertReq.getCourseId());
            return Result.fail("课程下章节已经存在");
        }

        // 检查课程是否存在
        if (!courseService.isExist(chapterInsertReq.getCourseId())) {
            log.error("新增章节课程不存在 courseId:{}", chapterInsertReq.getCourseId());
            return Result.fail("新增章节课程不存在");
        }

        // 新增章节
        Boolean flag = chapterService.insert(chapterInsertReq.getName(), chapterInsertReq.getCourseId());

        if (flag) {
            log.info("新增章节成功: {}", chapterInsertReq);
            return Result.succ("新增章节成功");
        } else {
            log.error("新增章节失败: {}", chapterInsertReq);
            return Result.fail("新增章节失败");
        }
    }

    @ApiOperation(value = "删除章节")
    @PostMapping("/delete")
    public Result delete(@RequestBody ChapterDeleteReq chapterDeleteReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(chapterDeleteReq.getId())) {
            log.error("删除章节参数为空 id:{}", chapterDeleteReq.getId());
            return Result.fail("删除章节参数为空");
        }

        // 检查章节是否存在
        if (!chapterService.isExist(chapterDeleteReq.getId())) {
            log.error("待删除章节不存在 id:{}", chapterDeleteReq.getId());
            return Result.fail("待删除章节不存在");
        }

        // 删除
        Boolean flag = chapterService.delete(chapterDeleteReq.getId());

        if (flag) {
            log.info("删除章节成功: {}", chapterDeleteReq);
            return Result.succ("删除章节成功");
        } else {
            log.error("删除章节失败: {}", chapterDeleteReq);
            return Result.fail("删除章节失败");
        }
    }

    @ApiOperation(value = "更新章节")
    @PostMapping("/update")
    public Result update(@RequestBody ChapterUpdateReq chapterUpdateReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(chapterUpdateReq.getId()) || ObjectUtils.isEmpty(chapterUpdateReq.getCourseId()) || StringUtils.isEmpty(chapterUpdateReq.getName())) {
            log.error("更新章节参数为空 id:{} courseId:{} name:{}", chapterUpdateReq.getId(), chapterUpdateReq.getCourseId(), chapterUpdateReq.getName());
            return Result.fail("更新章节参数为空");
        }

        // 通过id检查是否存在
        if (!chapterService.isExist(chapterUpdateReq.getId())) {
            log.error("待更新的章节不存在 id:{}", chapterUpdateReq.getId());
            return Result.fail("待更新的章节不存在");
        }

        // 通过courseId检查课程是否存在
        if (!courseService.isExist(chapterUpdateReq.getCourseId())) {
            log.error("待更新的章节的课程不存在 courseId:{}", chapterUpdateReq.getCourseId());
            return Result.fail("待更新的章节的课程不存在");
        }

        // 检查章节id是否在课程下
        if (!chapterService.isExist(chapterUpdateReq.getCourseId(), chapterUpdateReq.getId())) {
            log.error("课程下不存在此章节 id:{} courseId:{}", chapterUpdateReq.getId(), chapterUpdateReq.getCourseId());
            return Result.fail("课程下不存在此章节");
        }

        // 检查在课程下除开自己是否存在重名
        if (chapterService.isExist(chapterUpdateReq.getCourseId(), chapterUpdateReq.getId(), chapterUpdateReq.getName())) {
            log.error("课程的章节名已存在 id:{} courseId:{} name:{}", chapterUpdateReq.getId(), chapterUpdateReq.getCourseId(), chapterUpdateReq.getName());
            return Result.fail("课程的章节名已存在");
        }

        // 更新
        Boolean flag = chapterService.update(chapterUpdateReq.getId(), chapterUpdateReq.getName(), chapterUpdateReq.getCourseId());

        if (flag) {
            log.info("更新章节成功: {}", chapterUpdateReq);
            return Result.succ("更新章节成功");
        } else {
            log.error("更新章节失败: {}", chapterUpdateReq);
            return Result.fail("更新章节失败");
        }
    }

    @ApiOperation(value = "通过id获取章节")
    @PostMapping("/getById")
    public Result getById(@RequestBody ChapterGetByIdReq chapterGetByIdReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(chapterGetByIdReq.getId())) {
            log.error("通过id获取章节参数为空 id:{}", chapterGetByIdReq.getId());
            return Result.fail("通过id获取章节参数为空");
        }

        // 通过id检查章节是否存在
        if (!chapterService.isExist(chapterGetByIdReq.getId())) {
            log.error("通过id获取章节不存在 id:{}", chapterGetByIdReq.getId());
            return Result.fail("通过id获取章节不存在");
        }

        // 删除章节
        Chapter chapter = chapterService.getById(chapterGetByIdReq.getId());
        log.info("通过id获取章节 chapter:{}", chapter);

        return Result.succ(chapter);
    }

    @ApiOperation(value = "查询所有章节")
    @PostMapping("/getAll")
    public Result getAll(@RequestBody ChapterGetAllReq chapterGetAllReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(chapterGetAllReq.getCourseId())) {
            log.error("查询所有章节参数为空 courseId:{} name:{}", chapterGetAllReq.getCourseId(), chapterGetAllReq.getName());
            return Result.fail("查询所有章节参数为空");
        }

        // 查询所有
        List<Chapter> chapterList = chapterService.getAll(chapterGetAllReq.getName(), chapterGetAllReq.getCourseId());
        log.info("查询所有章节结果:{}", chapterList);

        return Result.succ(chapterList);
    }

}
