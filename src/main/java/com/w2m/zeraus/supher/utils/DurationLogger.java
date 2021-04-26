package com.w2m.zeraus.supher.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * DurationLogger Class
 * 
 * @author employee zerausCo
 *
 */
@Aspect
@Component
public class DurationLogger {
	private static Logger logger = LoggerFactory.getLogger(DurationLogger.class);

	@Around("execution(* *(..)) && @annotation(com.w2m.zeraus.supher.utils.Duration)")
	public Object log(ProceedingJoinPoint point) throws Throwable {
		// Start of the operation
		Date start = new Date();

		// Method invocation
		Object result = point.proceed();

		// End of the operation
		Date end = new Date();

		// Calculate the duration of the operation
		long duration = end.getTime() - start.getTime();

		long hours = TimeUnit.MILLISECONDS.toHours(duration);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(duration) % 60;
		long seconds = TimeUnit.MILLISECONDS.toSeconds(duration) % 60;
		long milliseconds = duration % 1000;

		String durationSt = String.format("%02d:%02d:%02d,%03d", hours, minutes, seconds, milliseconds);
		logger.info("Execution time = {}", durationSt);

		return result;
	}

}
