package com.jxcia.pt.controller;

import com.jxcia.pt.common.Result;
import com.jxcia.pt.dto.req.*;
import com.jxcia.pt.entity.Video;
import com.jxcia.pt.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/video")
@Slf4j
@Api(tags = "视频模块", value = "视频模块", description="视频模块接口")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @ApiOperation(value = "新增视频信息")
    @PostMapping("/insert")
    public Result insert(@RequestBody VideoInsertReq videoInsertReq) throws Exception {
        // 参数检查
        if (StringUtils.isEmpty(videoInsertReq.getName()) || StringUtils.isEmpty(videoInsertReq.getDescription()) ||
                ObjectUtils.isEmpty(videoInsertReq.getChapterId())) {
            log.error("新增视频参数为空 name:{} description:{} chatperId:{}", videoInsertReq.getName(), videoInsertReq.getDescription(),
                    videoInsertReq.getChapterId());
            return Result.fail("新增视频参数为空", null);
        }

        // 查看同一章节下是否存在同名的视频
        if (videoService.isExist(videoInsertReq.getChapterId(), videoInsertReq.getName())) {
            log.error("同一章节下存在同名的视频 name:{} description:{} chapterId:{}", videoInsertReq.getName(), videoInsertReq.getDescription(),
                    videoInsertReq.getChapterId());
            return Result.fail("同一章节下存在同名的视频", null);
        }

        // 新增视频
        Boolean flag = videoService.insert(videoInsertReq.getChapterId(), videoInsertReq.getName(), videoInsertReq.getDescription());

        if (flag) {
            log.info("新增视频成功");
            return Result.succ("新增视频成功", null);
        } else {
            log.error("新增视频失败");
            return Result.fail("新增视频失败", null);
        }
    }

    @ApiOperation(value = "删除视频信息")
    @PostMapping("/delete")
    public Result delete(@RequestBody VideoDeleteReq videoDeleteReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(videoDeleteReq.getId())) {
            log.error("删除视频信息参数为空 id:{}", videoDeleteReq.getId());
            return Result.fail("删除视频信息参数为空", null);
        }

        // 通过id查看视频信息是否存在
        if (!videoService.isExist(videoDeleteReq.getId())) {
            log.error("待删除视频信息不存在");
            return Result.fail("待删除视频信息不存在", null);
        }

        // 删除视频信息
        Boolean flag = videoService.delete(videoDeleteReq.getId());

        if (flag) {
            log.info("删除视频成功");
            return Result.succ("删除视频成功", null);
        } else {
            log.error("删除视频失败");
            return Result.fail("删除视频失败", null);
        }
    }

    @ApiOperation(value = "更新视频信息")
    @PostMapping("/update")
    public Result update(@RequestBody VideoUpdateReq videoUpdateReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(videoUpdateReq.getId()) || ObjectUtils.isEmpty(videoUpdateReq.getChapterId()) ||
                StringUtils.isEmpty(videoUpdateReq.getName()) || StringUtils.isEmpty(videoUpdateReq.getDescription())) {
            log.error("更新视频信息参数为空 id:{} chapterId:{} name:{} description:{}", videoUpdateReq.getId(), videoUpdateReq.getChapterId(),
                    videoUpdateReq.getName(), videoUpdateReq.getDescription());
            return Result.fail("更新视频参数为空", null);
        }

        // 检查待更新的视频信息是否存在
        if (!videoService.isExist(videoUpdateReq.getId())) {
            log.error("待更新的视频信息不存在 id:{}", videoUpdateReq.getId());
            return Result.fail("待更新的视频信息不存在", null);
        }

        // 检查同一章节下是否存在同名除开自己的视频信息
        if (videoService.isExist(videoUpdateReq.getId(), videoUpdateReq.getChapterId(), videoUpdateReq.getDescription())) {
            log.error("更新的视频信息名称已经存在");
            return Result.fail("更新的视频信息名称已经存在", null);
        }

        // 更新视频信息
        Boolean flag = videoService.update(videoUpdateReq.getId(), videoUpdateReq.getChapterId(), videoUpdateReq.getName(), videoUpdateReq.getDescription());

        if (flag) {
            log.info("更新视频成功");
            return Result.succ("更新视频成功", null);
        } else {
            log.error("更新视频失败");
            return Result.fail("更新视频失败", null);
        }
    }

    @ApiOperation(value = "根据id获取视频信息")
    @PostMapping("/getById")
    public Result getById(@RequestBody VideoGetByIdReq videoGetByIdReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(videoGetByIdReq.getId())) {
            log.error("根据id获取视频信息参数为空 id:{}", videoGetByIdReq.getId());
            return Result.fail("根据id获取视频信息参数为空", null);
        }

        // 查看视频信息是否存在
        if (!videoService.isExist(videoGetByIdReq.getId())) {
            log.error("根据id获取视频信息不存在");
            return Result.fail("根据id获取视频信息不存在", null);
        }

        // 根据id获取视频信息
        Video video = videoService.getById(videoGetByIdReq.getId());
        log.info("根据id获取视频信息结果:{}", video);

        return Result.succ(video);
    }

    @ApiOperation(value = "获取所有视频信息")
    @PostMapping("/getAll")
    public Result getAll(@RequestBody VideoGetAllReq videoGetAllReq) throws Exception {
        // 参数检查
        if (ObjectUtils.isEmpty(videoGetAllReq.getChapterId())) {
            log.error("获取所有视频信息参数为空 id:{}", videoGetAllReq.getChapterId());
            return Result.fail("获取所有视频信息参数为空", null);
        }

        // 获取所有视频信息
        List<Video> videos = videoService.getAll(videoGetAllReq.getChapterId(), videoGetAllReq.getName());
        log.info("获取所有视频信息结果:{}", videos);

        return Result.succ(videos);
    }

    @ApiOperation(value = "上传视频")
    @PostMapping("/upload")
    public Result upload(@RequestParam("id") Integer id, @RequestParam("videoFile") MultipartFile videoFile) throws Exception {
        if (ObjectUtils.isEmpty(videoFile) || ObjectUtils.isEmpty(id)) {
            log.error("上传视频参数为空 id:{} videoFile:{}", id, videoFile.getOriginalFilename());
            return Result.fail("上传视频参数为空", null);
        }

        // 通过id检查视频信息是否存在
        if (!videoService.isExist(id)) {
            log.error("待上传的视频信息不存在 id:{}", id);
            return Result.fail("待上传的视频信息不存在", null);
        }

        // 更新
        Boolean flag = videoService.upload(id, videoFile);

        if (flag) {
            log.info("上传视频成功");
            return Result.succ("上传视频成功", null);
        } else {
            log.error("上传视频失败");
            return Result.fail("上传视频失败", null);
        }
    }

}
