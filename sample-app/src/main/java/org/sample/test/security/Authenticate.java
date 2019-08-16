package org.sample.test.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.sample.test.utility.Utility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
public class Authenticate {
	private final static Logger logger = Logger
			.getLogger(Authenticate.class);

	@Value("${mha.portal.baseurl}")
	private String portalBaseUrl;

	@Value("${mha.portal.url.cso.ivgroups}")
	private String csoIVGroupsUrl;

	@Value("${mha.esu.baseurl}")
	private String esuBaseUrl;

	@Value("${mha.esu.url.checkeligibility}")
	private String checkEligibilityUrl;

	@Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
	public void controllerPointcut() {
	}

	@Pointcut("execution(* *(..))")
	public void methodPointcut() {
	}

	/**
	 * INTERCEPTS THE EXECUTION OF ALL CONTROLLER METHODS
	 * 
	 * @param joinPoint
	 * @return
	 */
	@Around("controllerPointcut() && methodPointcut()")
	public Object checkEligibility(ProceedingJoinPoint joinPoint) {
		Object respObj = null;
		try {

			// GET HEADER REQUEST
			ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
					.currentRequestAttributes();
			
			HttpServletRequest request = requestAttributes.getRequest();
		} catch (Throwable e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			Object error = Utility.processError(
					HttpStatus.INTERNAL_SERVER_ERROR, e);
			respObj = new ResponseEntity<Object>(error, HttpStatus.OK);
		}
		return respObj;
	}
}
