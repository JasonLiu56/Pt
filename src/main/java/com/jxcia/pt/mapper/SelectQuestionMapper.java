package com.jxcia.pt.mapper;

import com.jxcia.pt.entity.SelectQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SelectQuestionMapper {

    // 新增
    Boolean insert(SelectQuestion selectQuestion);

    // 通过id查看是否存在
    Boolean isExistById(@Param("id") Integer id);

    // 通过examId和question查看是否存在
    Boolean isExistByExamIdAndQuestion(@Param("examId") Integer examId, @Param("question") String question);

    // 通过examId和question查看是否存在(除开自己)
    Boolean isExistByNonIdAndExamIdQuestion(@Param("examId") Integer examId, @Param("id") Integer id, @Param("question") String question);

    // 删除
    Boolean delete(@Param("id") Integer id);

    // 更新
    Boolean update(@Param("id") Integer id, @Param("examId") Integer examId, @Param("question") String question, @Param("answerA") String answerA,
                   @Param("answerB") String answerB, @Param("answerC") String answerC, @Param("answerD") String answerD, @Param("answer") String answer,
                   @Param("analysis") String analysis, @Param("score") Integer score);

    // 通过id获取SelectQuestion
    SelectQuestion getById(@Param("id") Integer id);

    // 查找所有
    List<SelectQuestion> getAll(@Param("examId") Integer examId, @Param("question") String question);
}
