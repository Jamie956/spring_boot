package com.example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class ParamAspect {

    @Pointcut("execution(public * com.example..*.*(..))")
    public void paramPointCut() {
    }

    @Around("paramPointCut()")
    public Object ReplaceUserId(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature msg = (MethodSignature) joinPoint.getSignature();
        List<String> paramNameList = Arrays.asList(msg.getParameterNames());

        Object[] args = joinPoint.getArgs();

        Product product = (Product) args[0];
        product.setName("update");
        return joinPoint.proceed(args);
    }
}
