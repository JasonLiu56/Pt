package com.jxcia.pt.service;

import com.jxcia.pt.entity.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {

    // 新增
    Boolean insert(Integer chapterId, String name, String description);

    // 根据章节id查看是否为空
    Boolean isEmpty(Integer chapterId);

    // 根据id查看是否存在
    Boolean isExist(Integer id);

    // 根据章节id和名称查看是否存在
    Boolean isExist(Integer chapterId, String name);

    // 根据章节id和名称查看是否存在(除开自己)
    Boolean isExist(Integer id, Integer chapterId, String name);

    // 删除
    Boolean delete(Integer id);

    // 更新
    Boolean update(Integer id, Integer chapterId, String name, String description);

    // 通过id获取视频
    Video getById(Integer id);

    // 获取所有视频
    List<Video> getAll(Integer chapterId, String name);

    // 上传视频
    Boolean upload(Integer id, MultipartFile multipartFile) throws Exception;

}
