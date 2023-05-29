package com.caizhen.mvc.result;


import com.caizhen.pojo.entity.Result;

/**
 * <p>
 * 返回 结果状态 类
 * </p>
 *
 * @author caizhen
 * @since 2023-05-17
 */
public class ResultFactory {

    /**
     * 构建成功码
     * @param data
     * @return
     */
    public static Result buildSuccessResult(Object data) {
        return buildResult(ResultCode.SUCCESS, "成功", data);
    }
    /**
     * 构建成功码
     * @param data
     * @return
     */
    public static Result buildSuccessResult(Object data,String message) {
        return buildResult(ResultCode.SUCCESS, message, data);
    }


    /**
     * 返回失败码
     * @param message
     * @return
     */
    public static Result buildFailResult(String message) {
        return buildResult(ResultCode.FAIL, message, null);
    }


    public static Result buildResult(ResultCode resultCode, String message, Object data) {
        return buildResult(resultCode.code, message, data);
    }

    public static Result buildResult(int resultCode, String message, Object data) {
        return new Result(resultCode, message, data);
    }
}
