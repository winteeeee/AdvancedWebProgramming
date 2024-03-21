package com.kit.dormitory.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Aspect
@Component
public class ElapsedTimeMeasureAop {
    @Around("@annotation(com.kit.dormitory.annotation.ElapsedTimeLog)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        try {
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            System.out.println(joinPoint.toString() + ": " +stopWatch.getTotalTimeMillis());
            Object[] args = joinPoint.getArgs();
            Arrays.stream(args).forEach(v -> System.out.println("v = " + v));
        }
    }
}
