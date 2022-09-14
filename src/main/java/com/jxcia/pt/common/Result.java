package com.jxcia.pt.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private Integer code;

    private String msg;

    private Object object;

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
