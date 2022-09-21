package com.jxcia.pt.controller;

import com.jxcia.pt.common.Result;
import com.jxcia.pt.dto.req.*;
import com.jxcia.pt.dto.vo.PageVo;
import com.jxcia.pt.entity.Category;
import com.jxcia.pt.service.CategoryService;
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
@RequestMapping("/category")
@Slf4j
@Api(tags = "分类模块", value = "分类模块", description="分类模块接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "新增分类")
    @PostMapping("/insert")
    public Result insert(@RequestBody CategoryInsertReq categoryInsertReq) throws Exception {
        // 参数检查
        if (StringUtils.isEmpty(categoryInsertReq.getName())) {
            log.error("新增分类参数为空 name:{}", categoryInsertReq.getName());
            return Result.fail("新增分类参数为空");
        }

        // 检查是否存在同名的分类
        if (categoryService.isExist(categoryInsertReq.getName())) {
            log.error("新增分类已经存在 name:{}", categoryInsertReq.getName());
            return Result.fail("新增分类已经存在");
        }

        // 新增分类
        Boolean flag = categoryService.insert(categoryInsertReq.getName());

        if (flag) {
            log.info("新增分类成功: {}", categoryInsertReq);
            return Result.succ("新增分类成功");
        } else {
            log.error("新增分类失败: {}", categoryInsertReq);
            return Result.fail("新增分类失败");
        }
    }

    @ApiOperation(value = "删除分类")
    @PostMapping("/delete")
    public Result delete(@RequestBody CategoryDeleteReq categoryDeleteReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(categoryDeleteReq.getId())) {
            log.error("删除分类参数为空 id:{}", categoryDeleteReq.getId());
            return Result.fail("删除分类参数为空");
        }

        // 查看分类是否存在
        if (!categoryService.isExist(categoryDeleteReq.getId())) {
            log.error("待删除分类不存在 id:{}", categoryDeleteReq.getId());
            return Result.fail("待删除分类不存在");
        }

        // 删除分类
        Boolean flag = categoryService.delete(categoryDeleteReq.getId());

        if (flag) {
            log.info("删除分类成功: {}", categoryDeleteReq);
            return Result.succ("删除分类成功");
        } else {
            log.error("删除分类失败: {}", categoryDeleteReq);
            return Result.fail("删除分类失败");
        }
    }

    @ApiOperation(value = "更新分类")
    @PostMapping("/update")
    public Result update(@RequestBody CategoryUpdateReq categoryUpdateReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(categoryUpdateReq.getId()) || StringUtils.isEmpty(categoryUpdateReq.getName())) {
            log.error("更新分类参数为空 id:{} name:{}", categoryUpdateReq.getId(), categoryUpdateReq.getName());
            return Result.fail("更新分类参数为空");
        }

        // 查看分类是否存在
        if (!categoryService.isExist(categoryUpdateReq.getId())) {
            log.error("待更新分类不存在 id:{}", categoryUpdateReq.getId());
            return Result.fail("待更新分类不存在");
        }

        // 根据id和name判断是否存在除开自己的同名分类
        if (categoryService.isExist(categoryUpdateReq.getId(), categoryUpdateReq.getName())) {
            log.error("更新分类名称已存在 id:{}", categoryUpdateReq.getId());
            return Result.fail("更新分类名称已存在");
        }

        // 更新分类
        Boolean flag = categoryService.update(categoryUpdateReq.getId(), categoryUpdateReq.getName());

        if (flag) {
            log.info("更新分类成功: {}", categoryUpdateReq);
            return Result.succ("更新分类成功");
        } else {
            log.error("更新分类失败: {}", categoryUpdateReq);
            return Result.fail("更新分类失败");
        }
    }

    @ApiOperation(value = "通过id获取分类")
    @PostMapping("/getById")
    public Result getById(@RequestBody CategoryGetByIdReq categoryGetByIdReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(categoryGetByIdReq.getId())) {
            log.error("通过id获取分类参数为空 id:{}", categoryGetByIdReq.getId());
            return Result.fail("通过id获取分类参数为空");
        }

        // 查看是否存在
        if (!categoryService.isExist(categoryGetByIdReq.getId())) {
            log.error("通过id获取分类不存在");
            return Result.fail("通过id获取分类不存在");
        }

        // 通过id获取分类
        Category category = categoryService.getById(categoryGetByIdReq.getId());
        log.info("通过id获取分类 category:{}", category);

        return Result.succ(category);
    }


    @ApiOperation(value = "分页获取分类")
    @PostMapping("/getByPage")
    public Result getByPage(@RequestBody CategoryGetByPageReq categoryGetByPageReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(categoryGetByPageReq.getPageNum()) || ObjectUtils.isEmpty(categoryGetByPageReq.getPageSize())) {
            log.error("分页获取分类参数为空 pageNum:{} pageSize:{}", categoryGetByPageReq.getPageNum(), categoryGetByPageReq.getPageSize());
            return Result.fail("分页获取分类参数为空");
        }

        // 分页查询
        List<Category> categories = categoryService.getByPage(categoryGetByPageReq.getPageNum(), categoryGetByPageReq.getPageSize(),
                categoryGetByPageReq.getName());
        // 分页查询总数
        Integer total = categoryService.countByPage(categoryGetByPageReq.getName());
        PageVo pageVo = new PageVo(total, categories);
        log.info("分类获取分类结果 total:{} categories:{}", total, categories);

        return Result.succ(pageVo);
    }

}
