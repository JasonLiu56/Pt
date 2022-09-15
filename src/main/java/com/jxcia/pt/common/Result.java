package com.jxcia.pt.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "通用响应体")
public class Result implements Serializable {

    @ApiModelProperty(value = "响应码", notes = "响应码")
    private Integer code;

    @ApiModelProperty(value = "响应信息", notes = "响应信息")
    private String msg;

    @ApiModelProperty(value = "响应数据", notes = "响应数据")
    private Object data;

    public static Result succ(Integer code, String msg, Object object) {
        return new Result(code, msg, object);
    }

    public static Result succ(String msg, Object object) {
        return new Result(200, msg, object);
    }

    public static Result succ(Object object) {
        return new Result(200, "操作成功", object);
    }

    public static Result succ() {
        return new Result(200, "操作成功", null);
    }

    public static Result fail(Integer code, String msg, Object object) {
        return new Result(code, msg, object);
    }

    public static Result fail(String msg, Object object) {
        return new Result(400, msg, object);
    }

    public static Result fail(Object object) {
        return new Result(400, "操作失败", object);
    }

    public static Result fail() {
        return new Result(400, "操作失败", null);
    }

}
