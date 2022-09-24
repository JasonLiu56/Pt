package com.jxcia.pt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chapter {

    public Chapter(String name, Integer courseId) {
        this.name = name;
        this.courseId = courseId;
    }

    // 章节id
    private Integer id;

    // 章节名称
    private String name;

    // 课程id
    private Integer courseId;

    // 课程名称
    private String courseName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @JsonIgnore
    private Boolean isDeleted;

}
