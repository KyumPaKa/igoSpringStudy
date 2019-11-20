package com.example.spring02.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
// @Controller @Service @Repository
@Component // 스프링에서 관리하는 aop bean
@Aspect // 스프링에서 관리하는 aop bean
public class LogAdvice {

	private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);
	
//	포인트컷  - 실행 시점, Around - 실행 전후
//	Before, After, Around
//	컨트롤러, 서비스, dao의 모든 method 실행 전후에 logPrint method가 호출됨
//	excution(리턴자료형 class.method(매개변수))
//	@Around("execution(* com.example.spring02.controller..*Controller.*(..))"
//	+ " or execution(* com.example.spring02.service..*Impl.*(..))"
//	+ " or execution(* com.example.spring02.model..dao.*Impl.*(..))")
	public Object logPirnt(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = joinPoint.proceed(); // 기준 전후로 동작
		// class name
		String type = joinPoint.getSignature().getDeclaringTypeName(); // 호출한 class 이름
		String name = "";
		if(type.indexOf("Controller") > -1) {
			name = "Controller \t: ";
		} else if(type.indexOf("Service") > -1) {
			name = "ServiceImpl \t: ";
		} else if(type.indexOf("DAO") > -1 ) {
			name = "DAOImpl \t: ";
		}
		// method name
		logger.info(name + type + "." + joinPoint.getSignature().getName() + "()");
		// 매개변수
		logger.info(Arrays.toString(joinPoint.getArgs()));
		// 핵심로직으로 이동
		long end = System.currentTimeMillis();
		long time = end - start;
		logger.info("실행시간:" + time);
		return result;
	}
}
