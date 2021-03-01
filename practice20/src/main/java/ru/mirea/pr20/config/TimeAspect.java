package ru.mirea.pr20.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimeAspect {
    @Around("allServiceMethods()")
    public Object methodTimeLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;

        log.info("Method: {} from {}, Time: {} ms", methodName, className, timeElapsed);
        return result;
    }

    @Pointcut("within(ru.mirea.pr20.services.*)")
    public void allServiceMethods() {}
}
