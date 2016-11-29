package com.augmentun.exam.common;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LogMethodTimeAspect {
    private final Logger logger = Logger.getLogger(LogMethodTimeAspect.class);

    public void doBefore(JoinPoint jp) {
        //System.out.println("doBefore........................");
    }

    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object returnValue = pjp.proceed();
        String methodName = pjp.getSignature().getName();
        long end = System.currentTimeMillis();

        StringBuffer sb= new StringBuffer();
        sb.append(AppContext.getAppContext().getUserName());
        sb.append(":");
        sb.append(pjp.getTarget().getClass().getSimpleName());
        sb.append(":");
        sb.append(methodName);
        sb.append(":");
        sb.append(end - start);
        logger.info(sb.toString());
        return returnValue;
    }

    public void doAfter(JoinPoint jp) {
        //System.out.println("doAfter........................");
    }

    public void doThrowing(JoinPoint jp, Throwable ex) {
        //System.out.println("doThrowing.......................");
        //System.out.println(ex.getMessage());
    }

}
