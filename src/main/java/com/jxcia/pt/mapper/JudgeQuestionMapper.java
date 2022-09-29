package com.jxcia.pt.mapper;

import com.jxcia.pt.entity.JudgeQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JudgeQuestionMapper {

    // 新增
    Boolean insert(JudgeQuestion judgeQuestion);

    // 通过id查询是否存在
    Boolean isExistById(@Param("id") Integer id);

    // 通过examId和question查询是否存在
    Boolean isExistByExamIdAndQuestion(@Param("examId") Integer examId, @Param("question") String question);

    // 通过examId和question查询是否存在(除开自己)
    Boolean isExistByNonIdAndExamIdAndQuestion(@Param("examId") Integer examId, @Param("id") Integer id, @Param("question") String question);

    // 删除
    Boolean delete(@Param("id") Integer id);

    // 更新
    Boolean update(@Param("id") Integer id, @Param("question") String question, @Param("examId") Integer examId,
                   @Param("answer") String answer, @Param("analysis") String analysis, @Param("score") Integer score);

    // 通过id获取JudgeQuestion
    JudgeQuestion getById(@Param("id") Integer id);

    // 通过examId获取所有的JudgeQuestion
    List<JudgeQuestion> getAll(@Param("examId") Integer examId, @Param("question") String question);

}
