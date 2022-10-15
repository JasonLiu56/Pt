package com.jxcia.pt.mapper;

import com.jxcia.pt.entity.FillQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FillQuestionMapper {

    // 新增
    Boolean insert(FillQuestion fillQuestion);

    // 根据id查看FillQuestion是否存在
    Boolean isExistById(@Param("id") Integer id);

    // 根据id和examId查看是否存在
    Boolean isExistByIdAndExamId(@Param("id") Integer id, @Param("examId") Integer examId);

    // 根据examId和question查看FillQuestion是否存在
    Boolean isExistByExamIdAndQuestion(@Param("examId") Integer examId, @Param("question") String question);

    // 根据examId和question查看FillQuestion是否存在(除开自己本身)
    Boolean isExistByNonIdAndExamIdQuestion(@Param("id") Integer id, @Param("examId") Integer examId, @Param("question") String question);

    // 根据examId查看是否存在
    Boolean isExistByExamId(@Param("examId") Integer examId);

    // 删除
    Boolean delete(@Param("id") Integer id);

    // 修改
    Boolean update(@Param("id") Integer id, @Param("examId") Integer examId, @Param("question") String question, @Param("answer") String answer,
                   @Param("analysis") String analysis, @Param("score") Integer score);

    // 通过id获取FillQuestion
    FillQuestion getById(@Param("id") Integer id);

    // 分页查询FillQuestion
    List<FillQuestion> getAll(@Param("examId") Integer examId, @Param("question") String question);

}
