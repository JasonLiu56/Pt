package com.jxcia.pt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exam {

    public Exam(String name, String description) {
        this.name = name;
        this.description = description;
    }

    private Integer id;

    private String name;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @JsonIgnore
    private Boolean isDeleted;

}
