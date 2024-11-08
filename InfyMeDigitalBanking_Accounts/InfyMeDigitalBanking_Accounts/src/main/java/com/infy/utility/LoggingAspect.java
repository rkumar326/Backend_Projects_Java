package com.infy.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import com.infy.exception.InfyMeMobileException;


@Aspect
public class LoggingAspect {
	private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);
	
	@AfterThrowing(pointcut = "execution(* com.infy.service.*IMPL.*(..))", throwing = "exection")
	public void logServiceException(InfyMeMobileException exception) {
		LOGGER.error(exception.getMessage(),exception);
	}
}