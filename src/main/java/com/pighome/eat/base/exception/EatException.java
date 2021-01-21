package com.pighome.eat.base.exception;
import lombok.Getter;

/**
 * 自定义异常
 */
@Getter
public class EatException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String status;
    private String message;
    private Exception e;
    private Object data;

    public EatException(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public EatException(ExceptionStatus exceptionMessage) {
        this.status = exceptionMessage.getStatus();
        this.message = exceptionMessage.getMessage();
    }

    /**
     * 默认抛出300异常 业务异常
     * @param message 提示信息
     */
    public EatException(String message) {
        this.status =  ExceptionStatus.BUSINESS_ERR.getStatus();
        this.message = message;
    }

    public EatException(String status, Object data, String message) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public EatException(String msg, Exception e){
        this.e = e;
        this.message = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
