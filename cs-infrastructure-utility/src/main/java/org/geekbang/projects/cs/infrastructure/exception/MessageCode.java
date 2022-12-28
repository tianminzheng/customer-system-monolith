package org.geekbang.projects.cs.infrastructure.exception;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class MessageCode {
    
    /**
     * default ok
     */
    public static final MessageCode SUCCESS = MessageCode.build(200, "ok");
    
    public static final MessageCode UN_KNOW = MessageCode.build(10001, "unKnowError");
    
    public static final MessageCode SYSTEM_ERROR = MessageCode.build(10002, "systemError");
    
    public static final MessageCode COMPONENT_ERROR = MessageCode.build(10003, "componentError");
    
    public static final MessageCode RUNTIME_ERROR = MessageCode.build(10004, "runTimeError");
    
    /**
     * 远程调用错误
     */
    public static final MessageCode REMOTE_ERROR = MessageCode.build(10005, "callRemoteError");


    /**
     *
     */
    public static final MessageCode CHECK_ERROR = MessageCode.build(11000, "校验失败:{0}");

    private static final Logger logger = LoggerFactory.getLogger(MessageCode.class);

    private int status;

    private String message;

    MessageCode(int status, String message) {

        this.status = status;
        this.message = message;

    }

    /**
     * get new type exception category
     *
     * @param status  exception code
     * @param message exception message
     * @return this
     */
    public static MessageCode build(int status, String message) {
        return new MessageCode(status, message);
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

    /**
     * 获取替换的message
     *
     * @param sub 替换的字符串
     * @return finally string
     */
    public String getSubMessage(String[] sub) {

        if (this.getMessage() == null) {
            return "";
        }

        if (sub == null || sub.length <= 0) {
            return this.getMessage();
        }

        for (int i = 0, j = sub.length; i < j; i++) {
            if (!StringUtils.isNotEmpty(sub[i])) {
                sub[i] = "";
            }
        }
        try {
            return MessageFormat.format(this.getMessage(), sub);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return this.getMessage();
        }

    }

    @Override
    public String toString() {
        return "{\"status\":" + status + ",\"message\":\"" + message + '\"' + "}";
    }
}
