package com.edu.nju.alley.aop;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import com.edu.nju.alley.po.Log;
import com.edu.nju.alley.util.ReflectUtil;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
@Order(1)
public class ApiAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiAspect.class);

    @Pointcut("execution(public * com.edu.nju.alley.controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
    }

    @AfterReturning(value = "webLog()", returning = "ret")
    public void doAfterReturning(Object ret) {
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();

        //获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) return result;
        HttpServletRequest request = attributes.getRequest();

        //记录请求信息
        Log log = new Log();

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(ApiOperation.class)) {
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            log.setDescription(apiOperation.value());
        }

        long endTime = System.currentTimeMillis();

        String urlStr = request.getRequestURL().toString();

        log.setBasePath(StrUtil.removeSuffix(urlStr, URLUtil.url(urlStr).getPath()));
        log.setIp(request.getRemoteUser());
        log.setMethod(request.getMethod());
        log.setParameter(ReflectUtil.getParameter(method, joinPoint.getArgs()));
        log.setResult(result);
        log.setSpendTime((int) (endTime - startTime));
        log.setStartTime(startTime);
        log.setUri(request.getRequestURI());
        log.setUrl(request.getRequestURL().toString());

        LOGGER.info("{}", JSONUtil.parse(log));
        return result;
    }


}
