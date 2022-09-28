package com.jxcia.pt.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "通过id获取填空题请求体")
public class FillQuestionGetByIdReq {

    @ApiModelProperty(value = "填空题id", notes = "填空题id")
    private Integer id;

}
