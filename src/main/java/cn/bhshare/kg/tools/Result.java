package cn.bhshare.kg.tools;

import lombok.Data;

import java.util.List;

@Data
public class Result<T> {

    private int code;
    private String msg;
    private T data;
    private int count;


    public Result(int code, String msg, T data, int count) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }

    public static <T> Result ok(int code, String msg, T data, int count) {
        return new Result<>(code, msg, data, count);
    }

    public static Result ok(List<?> data) {
        return new Result<>(200, "ok", data, data.size());
    }

    public static Result err(String msg) {
        return new Result<>(0, msg, "", -1);
    }
    public static Result err(int code, String msg) {
        return new Result<>(code, msg, "", -1);
    }

}
