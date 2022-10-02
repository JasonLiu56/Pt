package com.jxcia.pt.mapper;

import com.jxcia.pt.entity.Quiz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuizMapper {

    // 新增
    Boolean insert(Quiz quiz);

    // 通过id判断是否存在
    Boolean isExistById(@Param("id") Integer id);

    // 通过examId和uid判断是否已经存在
    Boolean isExistByIdAndUid(@Param("id") Integer id, @Param("uid") Integer uid);

    // 删除
    Boolean delete(@Param("id") Integer id, @Param("uid") Integer uid);

    // 更新总分
    Boolean update(@Param("id") Integer id, @Param("totalScore") Integer totalScore);

    // 通过id获取Quiz
    Quiz getById(@Param("id") Integer id, @Param("uid") Integer uid);

    // 通过uid和考试名称分页查找Quiz
    List<Quiz> getByPage(@Param("uid") Integer uid, @Param("examName") String examName);

    // 分页统计总数
    Integer countByPage(@Param("uid") Integer uid, @Param("examName") String examName);

}
