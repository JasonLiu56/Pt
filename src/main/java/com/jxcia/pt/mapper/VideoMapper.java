package com.jxcia.pt.mapper;

import com.jxcia.pt.entity.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VideoMapper {

    // 新增
    Boolean insert(Video video);

    // 通过id判断是否存在
    Boolean isExistById(@Param("id") Integer id);

    // 通过chapterId判断视频是否为空
    Boolean isExistByChapterId(@Param("chapterId") Integer chapterId);

    // 通过章节id和视频名称判断是否存在
    Boolean isExistByChapterIdAndName(@Param("chapterId") Integer chapterId, @Param("name") String name);

    // 同一章节下名字是否存在(除开本身)
    Boolean isExistByNonIdAndChapterIdName(@Param("id") Integer id, @Param("chapterId") Integer chapterId, @Param("name") String name);

    // 删除
    Boolean delete(@Param("id") Integer id);

    // 更新url
    Boolean updateUrl(@Param("id") Integer id, @Param("url") String url);

    // 更新
    Boolean update(@Param("id") Integer id, @Param("chapterId") Integer chapterId, @Param("name") String name, @Param("description") String description);

    // 根据id查询视频
    Video getById(@Param("id") Integer id);

    // 查询所有
    List<Video> getAll(@Param("chapterId") Integer chatperId, @Param("name") String name);

}
