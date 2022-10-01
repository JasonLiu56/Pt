package com.jxcia.pt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video implements Serializable {

    public Video(String name, String description, Integer chapterId) {
        this.name = name;
        this.description = description;
        this.chapterId = chapterId;
    }

    // 视频id
    private Integer id;

    // 视频名称
    private String name;

    // 视频描述
    private String description;

    // 视频url
    private String url;

    // 章节id
    private Integer chapterId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @JsonIgnore
    private Boolean isDeleted;

}
