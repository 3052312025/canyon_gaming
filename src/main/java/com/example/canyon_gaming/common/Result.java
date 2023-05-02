package com.example.canyon_gaming.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private String code;//200是正常，非200表示异常
    private String msg;
    private Object data;

    //数据正常
    public static Result success(Object data) {
        return success("200", "操作成功", data);
    }


    public static Result success(String code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }


    //数据异常
    public static Result fail(String msg) {
        return fail("400", msg, null);
    }


    public static Result fail(String code, String msg) {
        return fail(code, msg, null);
    }


    public static Result fail(String code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
