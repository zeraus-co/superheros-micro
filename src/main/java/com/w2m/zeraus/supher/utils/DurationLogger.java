package com.w2m.zeraus.supher.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DurationLogger {
	private static Logger logger = LoggerFactory.getLogger(DurationLogger.class);

	@Around("execution(* *(..)) && @annotation(com.w2m.zeraus.supher.utils.Duration)")
	public Object log(ProceedingJoinPoint point) throws Throwable {
		Date start = new Date();

		Object result = point.proceed();

		Date end = new Date();

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
