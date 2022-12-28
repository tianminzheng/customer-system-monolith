package org.geekbang.projects.cs.infrastructure.aop;

import com.alibaba.fastjson.JSON;
import org.geekbang.projects.cs.infrastructure.vo.BaseReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Slf4j
@Component
public class LogAspect {

    public final static String TRACE_LOG_ID = "traceId";

    /**
     * 切面表达式  此次切面为service的实现类
     */
    @Pointcut("execution(* org.geekbang.projects.cs.*.service.*.impl.*.*(..))")
    public void logPointcut() {

    }

    /**
     * 打印日志
     *
     * @param pjp 入参 traceId从第一个参数获取
     * @return 结果
     * @throws Throwable 异常
     */
    @Around("logPointcut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        String traceId = MDC.get(TRACE_LOG_ID);
        if (StringUtils.isBlank(traceId)) {
            if (pjp.getArgs().length > 0 && pjp.getArgs()[0] instanceof BaseReq) {
                BaseReq req = (BaseReq) pjp.getArgs()[0];
                if (StringUtils.isNotBlank(req.getTraceId())) {
                    traceId = req.getTraceId();
                }
            }
            if (StringUtils.isNotBlank(traceId)) {
                MDC.put(TRACE_LOG_ID, traceId);
            } else {
                MDC.put(TRACE_LOG_ID, UUID.randomUUID().toString());
            }
        }

        long startTime = System.currentTimeMillis();
        // 打印请求参数
        log.info("入参:{}", JSON.toJSONString(pjp.getArgs()));

        Object result = pjp.proceed();
        // 打印出参
        log.info("耗时{}ms,出参:{}", System.currentTimeMillis() - startTime, JSON.toJSONString(result));
        return result;
    }

}
