package com.jxcia.pt.mapper;

import com.jxcia.pt.entity.Exam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExamMapper {

    // 新增
    Boolean insert(Exam exam);

    // 根据id查询是否存在
    Boolean isExistById(@Param("id") Integer id);

    // 根据name查询是否存在
    Boolean isExistByName(@Param("name") String name);

    // 根据name查询是否存在(除开自己)
    Boolean isExistByNonIdAndName(@Param("id") Integer id, @Param("name") String name);

    // 删除
    Boolean delete(@Param("id") Integer id);

    // 更新
    Boolean update(@Param("id") Integer id, @Param("name") String name, @Param("description") String description);

    // 通过id获取Exam
    Exam getById(@Param("id") Integer id);

    // 分页获取Exam
    List<Exam> getByPage(@Param("name") String name, @Param("description") String description);

    // 统计分页Exam总数
    Integer countByPage(@Param("name") String name, @Param("description") String description);

}
