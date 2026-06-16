package com.rk.cybergamingmanagement.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.rk.cybergamingmanagement.service.impl.GamingGearServiceImpl.createGear(..))")
    public void createGearPointcut() {}

    @Pointcut("execution(* com.rk.cybergamingmanagement.service.impl.GamingGearServiceImpl.updateGear(..))")
    public void updateGearPointcut() {}

    @Pointcut("execution(* com.rk.cybergamingmanagement.service.impl.GamingGearServiceImpl.patchGear(..))")
    public void patchGearPointcut() {}

    @Pointcut("createGearPointcut() || updateGearPointcut() || patchGearPointcut()")
    public void createOrUpdatePointcut() {}

    @Before("createOrUpdatePointcut()")
    public void logBeforeCreateOrUpdate(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        logger.info("==> [AOP LOG] Method called: {}.{}()", className, methodName);
    }
}
