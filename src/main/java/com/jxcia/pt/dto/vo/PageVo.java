package com.jxcia.pt.dto.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "分页响应体")
public class PageVo {

    @ApiModelProperty(value = "元素总数", notes = "元素总数")
    private Integer total;

    @ApiModelProperty(value = "元素集合", notes = "元素集合")
    private Object items;

}
