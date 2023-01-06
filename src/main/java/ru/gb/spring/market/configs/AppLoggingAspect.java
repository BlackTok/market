package ru.gb.spring.market.configs;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class AppLoggingAspect {
    @Around("execution(public * ru.gb.spring.market.services.*.*(..))")
    public Object methodProfiling(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("Start profiling!");
        long begin = System.currentTimeMillis();
        Object out = joinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        log.debug((MethodSignature) joinPoint.getSignature() + " duration: " + duration);
        log.debug("End profiling!");

        return out;
    }
}


