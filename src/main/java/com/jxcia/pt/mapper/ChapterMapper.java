package com.jxcia.pt.mapper;

import com.jxcia.pt.entity.Chapter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChapterMapper {

    // 新增
    Boolean insert(Chapter chapter);

    // 通过id查询章节存在
    Boolean isExistById(@Param("id") Integer id);

    // 通过chapterId查询是否存在
    Boolean isExistByCourseId(@Param("courseId") Integer courseId);

    // 通过name查询章节是否存在
    Boolean isExistByNameAndCourseId(@Param("name") String name, @Param("courseId") Integer courseId);

    // 查询章节是否在课程下
    Boolean isExistByIdAndCourseId(@Param("id") Integer id, @Param("courseId") Integer courseId);

    // 通过同一个course下面name是否已经存在(除开自己)
    Boolean isExistByNonIdAndNameCourseId(@Param("id") Integer id, @Param("courseId") Integer courseId, @Param("name") String name);

    // 删除
    Boolean delete(@Param("id") Integer id);

    // 更新
    Boolean update(@Param("id") Integer id, @Param("name") String name, @Param("courseId") Integer courseId);

    // 通过id获取章节
    Chapter getById(@Param("id") Integer id);

    // 分页获取章节
    List<Chapter> getAll(@Param("name") String name, @Param("courseId") Integer courseId);

}
