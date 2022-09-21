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
public class Course {

    public Course(String name, Integer categoryId) {
        this.name = name;
        this.categoryId = categoryId;
    }

    // 课程id
    private Integer id;

    // 课程名称
    private String name;

    // 分类id
    private Integer categoryId;

    // 分类名称
    private String categoryName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @JsonIgnore
    private Boolean isDeleted;

}
