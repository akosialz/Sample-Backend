package org.sample.test.logging;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.framework.ProxyFactory;

import com.google.gson.Gson;

@Aspect
public class SampleLogger {
	
	private final static Logger logger = Logger.getLogger(SampleLogger.class);
	
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void controllerPointcut() {}
	
    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void servicePointcut() {}
    
    @Pointcut("target(org.springframework.data.repository.Repository)")
    public void daoPointCut() {}

    @Pointcut("execution(* *(..))")
    public void methodPointcut() {}
    
	@Around("(controllerPointcut() || servicePointcut() || daoPointCut()) && methodPointcut()")
    public Object methodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
    	Object retObj = null;
    	try {
    		String classType = getClassType(joinPoint.getTarget().getClass().getSimpleName());
    		// METHOD DETAILS FORMAT: ClassName.methodName(Object...args)
    		String methodDetails = getMethodDetails(joinPoint, classType);
    		String logStartStr = String.format(
    				"%s METHOD START ----- %s", classType, methodDetails);
    		logger.debug(logStartStr);
    		retObj = joinPoint.proceed();
    		String logEndStr = String.format("%s METHOD END ----- %s\n%s", 
    				classType, methodDetails, getMethodReturn(retObj));
    		logger.debug(logEndStr);
    	} catch (Exception e) {
    		throw e;
    	}
    	return retObj;
    }
	
	@SuppressWarnings("rawtypes")
	private String getMethodDetails(JoinPoint joinPoint, String classType) {
		StringBuilder methodDetails = new StringBuilder();
		String className = null;
		// GET CLASS NAME
		if ("DAO".equals(classType)) {
			ProxyFactory factory = new ProxyFactory(joinPoint.getTarget());
			Class[] classes = factory.getProxiedInterfaces();
			className = classes[0].getSimpleName();
		} else {
			className = joinPoint.getTarget().getClass().getSimpleName();
		}
		// GET METHOD NAME
		String methodName = joinPoint.getSignature().getName();
		// GET METHOD ARGS
		String methodArgs = getMethodArgs(joinPoint.getArgs());
		methodDetails.append(className);
		methodDetails.append(".");
		methodDetails.append(methodName);
		methodDetails.append("(");
		methodDetails.append(methodArgs);
		methodDetails.append(")");
		return methodDetails.toString();
	}
    
	private String getMethodReturn(Object retObj) throws IOException {
		return (null != retObj) ? 
				new Gson().toJson(retObj) : "";
    }
    
    private String getMethodArgs(Object[] args) {
    	String methodArgs = "";
    	StringBuilder builder = new StringBuilder("");
    	if (0 != args.length) {
    		for (Object arg : args) {
    			builder.append(arg);
    			builder.append(",");
    		}
    		methodArgs = builder.substring(0, builder.length() - 1);
    	}
    	return methodArgs;
    }
    
    private String getClassType(String className) {
    	String classType = null;
    	if (className.toLowerCase().contains("controller")) {
    		classType = "CONTROLLER";
    	} else if (className.toLowerCase().contains("service")) {
    		classType = "SERVICE";
    	} else {
    		classType = "DAO";
    	}
    	return classType;
    }
}
