package org.geekbang.projects.cs.infrastructure.aop;

import org.geekbang.projects.cs.infrastructure.exception.BizException;
import org.geekbang.projects.cs.infrastructure.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 请求体为空处理
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public Result exceptionHandler(HttpServletRequest req, HttpMessageNotReadableException e) {
//        log.error("请求体request body不能为空：", e);
        return Result.error("请求体request body不能为空!");
    }

    /**
     * 参数校验异常处理
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result exceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e) {
//        log.error("参数校验异常：", e);
        return Result.error(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 请求方法不支持异常处理
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Result exceptionHandler(HttpServletRequest req, HttpRequestMethodNotSupportedException e) {
//        log.error("参数校验异常：", e);
        return Result.error(e.getMessage());
    }

    /**
     * 业务异常处理
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    public Result exceptionHandler(HttpServletRequest req, BizException e) {
        log.error("业务异常：", e);
        return Result.error(e.getMessageCode().getStatus(), e.getMessage());
    }

    /**
     * 未知异常处理
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("系统异常：", e);
        return Result.error("系统内部异常!");
    }
}
