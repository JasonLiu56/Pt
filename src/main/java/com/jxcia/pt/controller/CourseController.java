package com.jxcia.pt.controller;

import com.jxcia.pt.common.Result;
import com.jxcia.pt.dto.req.*;
import com.jxcia.pt.dto.vo.PageVo;
import com.jxcia.pt.entity.Course;
import com.jxcia.pt.service.CategoryService;
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
@RequestMapping("/course")
@Slf4j
@Api(tags = "课程模块", value = "课程模块", description="课程模块接口")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    public CategoryService categoryService;

    @ApiOperation(value = "新增课程")
    @PostMapping("/insert")
    public Result insert(@RequestBody CourseInsertReq courseInsertReq) throws Exception {
        // 参数检查
        if (StringUtils.isEmpty(courseInsertReq.getName()) || ObjectUtils.isEmpty(courseInsertReq.getCategoryId())) {
            log.error("新增课程参数为空 name:{} categoryId:{}", courseInsertReq.getName(), courseInsertReq.getCategoryId());
            return Result.fail("新增课程参数为空");
        }

        // 检查课程是否存在
        if (courseService.isExist(courseInsertReq.getName())) {
            log.error("新增课程已经存在 name:{} categoryId:{}", courseInsertReq.getName(), courseInsertReq.getCategoryId());
            return Result.fail("新增课程已经存在");
        }

        // 检查categoryId是否存在
        if (!categoryService.isExist(courseInsertReq.getCategoryId())) {
            log.error("新增课程分类不存在 categoryId:{}", courseInsertReq.getCategoryId());
            return Result.fail("新增课程分类不存在");
        }

        // 新增课程
        Boolean flag = courseService.insert(courseInsertReq.getName(), courseInsertReq.getCategoryId());

        if (flag) {
            log.info("新增课程成功: {}", courseInsertReq);
            return Result.succ("新增课程成功");
        } else {
            log.error("新增课程失败: {}", courseInsertReq);
            return Result.fail("新增课程失败");
        }
    }

    @ApiOperation(value = "删除课程")
    @PostMapping("/delete")
    public Result delete(@RequestBody CourseDeleteReq courseDeleteReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(courseDeleteReq.getId())) {
            log.error("删除课程参数为空 id:{}", courseDeleteReq.getId());
            return Result.fail("删除参数为空");
        }

        // 检查课程是否存在
        if (!courseService.isExist(courseDeleteReq.getId())) {
            log.error("待删除的课程不存在 id:{}", courseDeleteReq);
            return Result.fail("待删除的课程不存在");
        }

        // 删除课程
        Boolean flag = courseService.delete(courseDeleteReq.getId());

        if (flag) {
            log.info("删除课程成功: {}", courseDeleteReq);
            return Result.succ("删除课程成功");
        } else {
            log.error("删除课程失败: {}", courseDeleteReq);
            return Result.fail("删除课程失败");
        }
    }

    @ApiOperation(value = "更新课程")
    @PostMapping("/update")
    public Result update(@RequestBody CourseUpdateReq courseUpdateReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(courseUpdateReq.getId()) || ObjectUtils.isEmpty(courseUpdateReq.getCategoryId()) || StringUtils.isEmpty(courseUpdateReq.getName())) {
            log.error("更新课程参数为空 id:{} categoryId:{}, name:{}", courseUpdateReq.getId(), courseUpdateReq.getCategoryId(), courseUpdateReq.getName());
            return Result.fail("更新课程参数为空");
        }

        // 查看课程分类是否存在
        if (!categoryService.isExist(courseUpdateReq.getCategoryId())) {
            log.error("更新课程课程分类不存在 categoryId:{}", courseUpdateReq.getCategoryId());
            return Result.fail("更新课程课程分类不存在");
        }

        // 查看待更新的课程是否存在
        if (!categoryService.isExist(courseUpdateReq.getId())) {
            log.error("待更新的课程不存在 id:{}", courseUpdateReq.getId());
            return Result.fail("待更新的课程不存在");
        }
        
        // 查看课程是否存在同名
        if (courseService.isExist(courseUpdateReq.getId(), courseUpdateReq.getName())) {
            log.error("更新课程课程已存在 id:{} name:{}", courseUpdateReq.getId(), courseUpdateReq.getName());
            return Result.fail("更新课程课程已存在");
        }

        // 更新课程
        Boolean flag = courseService.update(courseUpdateReq.getId(), courseUpdateReq.getName(), courseUpdateReq.getCategoryId());

        if (flag) {
            log.info("更新课程成功: {}", courseUpdateReq);
            return Result.succ("更新课程成功");
        } else {
            log.error("更新课程失败: {}", courseUpdateReq);
            return Result.fail("更新课程失败");
        }
    }

    @ApiOperation(value = "根据id获取课程")
    @PostMapping("/getById")
    public Result getById(@RequestBody CourseGetByIdReq courseGetByIdReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(courseGetByIdReq.getId())) {
            log.error("根据id获取课程参数为空 id:{}", courseGetByIdReq.getId());
            return Result.fail("根据id获取课程参数为空");
        }

        // 根据id查询课程是否存在
        if (!courseService.isExist(courseGetByIdReq.getId())) {
            log.error("根据id获取课程不存在 id:{}", courseGetByIdReq.getId());
            return Result.fail("根据id获取课程不存在");
        }

        Course course = courseService.getById(courseGetByIdReq.getId());
        log.info("根据id获取课程 course:{}", course);

        return Result.succ(course);
    }


    @ApiOperation(value = "分页查询课程")
    @PostMapping("/getByPage")
    public Result getByPage(@RequestBody CourseGetByPageReq courseGetByPageReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(courseGetByPageReq.getPageNum()) || ObjectUtils.isEmpty(courseGetByPageReq.getPageSize())) {
            log.error("分页查询课程参数为空 pageNum:{} pageSize:{}", courseGetByPageReq.getPageNum(), courseGetByPageReq.getPageSize());
            return Result.fail("分页查询课程参数为空");
        }

        // 分页查询
        List<Course> courses = courseService.getByPage(courseGetByPageReq.getPageNum(), courseGetByPageReq.getPageSize(),
                courseGetByPageReq.getName(), courseGetByPageReq.getCategoryId());
        // 统计分页总数
        Integer total = courseService.countByPage(courseGetByPageReq.getName(), courseGetByPageReq.getCategoryId());
        PageVo pageVo = new PageVo(total, courses);
        log.info("分页查询课程 total:{} courses:{}", total, courses);

        return Result.succ(pageVo);
    }

}
