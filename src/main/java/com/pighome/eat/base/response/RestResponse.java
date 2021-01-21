package com.pighome.eat.base.response;


import com.pighome.eat.base.exception.ExceptionStatus;
import lombok.Getter;

/**
 * @author zoo
 * @date 2021/1/21
 */
@Getter
public class RestResponse<T> {

    public static final String OK_CODE = "200";

    public static final String OK_MESSAGE = "成功";
    /**
     * 状态码
     */
    private final String status;

    /**
     * 状态信息
     */
    private final String message;


    /**
     * 异常ID
     */
    private String eid = "";

    /**
     * 数据
     */
    private T data;

    private RestResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    private RestResponse(String status, String message, String eid) {
        this.status = status;
        this.message = message;
        this.eid = eid;
    }

    private RestResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> RestResponse<T> success() {
        return new RestResponse<>(OK_CODE, OK_MESSAGE);
    }

    public static <T> RestResponse<T> success(T data) {
        return new RestResponse<>(OK_CODE, OK_MESSAGE, data);
    }

    public static <T> RestResponse<T> success(String message, T data) {
        return new RestResponse<>(OK_CODE, message, data);
    }


    public static <T> RestResponse<T> error(String status, String message) {
        return new RestResponse<>(status, message);
    }

    public static <T> RestResponse<T> error(String status, String message, String eid) {
        return new RestResponse<>(status, message, eid);
    }

    public static <T> RestResponse<T> error(String status, String message, T data) {
        return new RestResponse<>(status, message, data);
    }

    public static <T> RestResponse<T> error(String message) {
        return new RestResponse<>(ExceptionStatus.BUSINESS_ERR.getStatus(), message);
    }

    public static <T> RestResponse<T> error(String message, T data) {
        return new RestResponse<>(ExceptionStatus.BUSINESS_ERR.getStatus(), message, data);
    }
}
