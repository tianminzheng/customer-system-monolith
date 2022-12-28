package org.geekbang.projects.cs.infrastructure.vo;

import org.geekbang.projects.cs.infrastructure.exception.MessageCode;
import org.slf4j.MDC;

import java.io.Serializable;

public class Result<T> implements Serializable {

    public final static String TRACE_LOG_ID = "traceId";

    /**
     * 状态码：200正常，500异常
     */
    private int status;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 数据体
     */
    private T data;
    /**
     * traceId
     */
    private String traceId;
    /**
     * errMsg
     */
    private String errMsg;

    public boolean rsIsOk() {
        return status == MessageCode.SUCCESS.getStatus();
    }

    public static Result success() {
        return new Result(MessageCode.SUCCESS.getStatus(), MessageCode.SUCCESS.getMessage(), MDC.get(TRACE_LOG_ID));
    }

    public static Result success(MessageCode  messageCode) {
        return new Result(messageCode.getStatus(), messageCode.getMessage(), MDC.get(TRACE_LOG_ID));
    }

    public static <T> Result success(T data) {
        return new Result(MessageCode.SUCCESS.getStatus(), MessageCode.SUCCESS.getMessage(), data, MDC.get(TRACE_LOG_ID));
    }

    public static <T> Result success(int code, String message, T data) {
        return new Result(code, message, data, MDC.get(TRACE_LOG_ID));
    }


    public static Result error() {
        return new Result(MessageCode.SYSTEM_ERROR.getStatus(), MessageCode.SYSTEM_ERROR.getMessage(), MDC.get(TRACE_LOG_ID));
    }

    public static Result error(int code, String message) {
        return new Result(code, message, MDC.get(TRACE_LOG_ID));
    }

    public static Result error(String message) {
        return new Result(MessageCode.SYSTEM_ERROR.getStatus(), message, MDC.get(TRACE_LOG_ID));
    }

    public static <T> Result error(String message, T data) {
        return new Result(MessageCode.SYSTEM_ERROR.getStatus(), message, data, MDC.get(TRACE_LOG_ID));
    }

    public static <T> Result error(int code, String message, T data) {
        return new Result(code, message, data, MDC.get(TRACE_LOG_ID));
    }

    public Result() {
    }



    public Result(int status, String message, String traceId) {
        this.status = status;
        this.message = message;
        this.traceId = traceId;
    }



    public Result(int status, String message, T data, String traceId) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.traceId = traceId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", traceId='" + traceId + '\'' +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }


}
