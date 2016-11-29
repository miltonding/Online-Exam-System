package com.augmentun.exam.common;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

public class LogMethodTime implements MethodInterceptor {
    private final Logger logger = Logger.getLogger(LogMethodTime.class);
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
/*        System.out.println("spring aop......................");
        long start = System.currentTimeMillis();*/
        Object returnObject = invocation.proceed();

/*        long end = System.currentTimeMillis();
        StringBuffer sb= new StringBuffer();
        sb.append(AppContext.getAppContext().getUserName());
        sb.append(":");
        sb.append(invocation.getMethod().getDeclaringClass().getSimpleName());
        sb.append(":");
        sb.append(invocation.getMethod().getName());
        sb.append(":");
        sb.append(end - start);
        logger.error(sb.toString());*/
        return returnObject;
    }

}
