package com.caizhen.mvc.result;

/**
 * <p>
 * 返回值枚举类
 * </p>
 *
 * @author caizhen
 * @since 2023-05-17
 */

public enum ResultCode {
    /**
     * 成功
     */
    SUCCESS(200),
    /**
     * 失败
     */
    FAIL(400),
    /**
     * 未授权
     */
    UNAUTHORIZED(401),
    /**
     * 未找到
     */
    NOT_FOUND(404),
    /**
     *
     */
    INTERNAL_SERVER_ERROR(500);

    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}
