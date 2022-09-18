package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "分页请求体")
public class PageReq {

    @ApiModelProperty(value = "分页num", notes = "分页num")
    private Integer pageNum;

    @ApiModelProperty(value = "分页size", notes = "分页size")
    private Integer pageSize;

}
