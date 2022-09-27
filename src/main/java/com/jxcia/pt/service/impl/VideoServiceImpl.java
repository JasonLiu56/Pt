package com.jxcia.pt.service.impl;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.jxcia.pt.entity.Video;
import com.jxcia.pt.mapper.VideoMapper;
import com.jxcia.pt.service.VideoService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Value("${fdfs.visit-host}")
    private String hostIp;

    @Autowired
    private FastFileStorageClient storageClient;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean insert(Integer chapterId, String name, String description) {
        Video video = new Video(name, description, chapterId);
        return videoMapper.insert(video);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isEmpty(Integer chapterId) {
        return !videoMapper.isExistByChapterId(chapterId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer id) {
        return videoMapper.isExistById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer chapterId, String name) {
        return videoMapper.isExistByChapterIdAndName(chapterId, name);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean isExist(Integer id, Integer chapterId, String name) {
        return videoMapper.isExistByNonIdAndChapterIdName(id, chapterId, name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean delete(Integer id) {
        // 通过id获取video
        Video video = videoMapper.getById(id);
        if (!StringUtils.isEmpty(video.getUrl())) {
            // 删除Fastdfs视频
            StorePath storePath = StorePath.parseFromUrl(video.getUrl());
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        }

        return videoMapper.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean update(Integer id, Integer chapterId, String name, String description) {
        return videoMapper.update(id, chapterId, name, description);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Video getById(Integer id) {
        return videoMapper.getById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Video> getAll(Integer chapterId, String name) {
        return videoMapper.getAll(chapterId, name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean upload(Integer id, MultipartFile videoFile) throws Exception {
        // 上传到Fastdfs
        StorePath storePath = storageClient.uploadFile(videoFile.getInputStream(), videoFile.getSize(),
                FilenameUtils.getExtension(videoFile.getOriginalFilename()), null);
        String filePath = storePath.getFullPath();
        String url = hostIp + "/" + filePath;

        // 更新视频信息
        return videoMapper.updateUrl(id, url);
    }
}
