package egovframework.com.cmmn.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import egovframework.com.cmmn.intercepter.CommIntercepter;



public class DditSysLogAspect {

	private final static Logger LOGGER = LoggerFactory.getLogger(CommIntercepter.class);

	public void logAfter(ProceedingJoinPoint joinPoint, Object returnVal) throws Throwable {
		LOGGER.debug("====================logAfter aop 실행===============================");
		LOGGER.debug("logAfter : {}",joinPoint);
		LOGGER.debug("logAfter : {}",returnVal);
		
	}
	public Object logView(ProceedingJoinPoint joinPoint) throws Throwable {
	
		
		//실행될 service 객체 제어를 하겠다..

		StopWatch stopWatch = new StopWatch();

		try {
			
			LOGGER.debug("====================logView aop 실행===============================");
			LOGGER.debug("logView {}",joinPoint.getArgs());
		
			//service 외 메소드를 실행해라...
			stopWatch.start();

			
			//실행된 결과 값을 반환을 받게다.
			Object retValue = joinPoint.proceed();
			
			
			LOGGER.debug("====================logView aop 실행 결과===============================");
			LOGGER.debug("logview result : {}",retValue);
			
			//처리된 프로세스를 controller 또는 호출자에게 값을 전달
			return retValue;
			
		} catch (Throwable e) {
			throw e;
			
		} finally {
			
			//제어를 중지하고 aop객체를 소멸하겠다.
			stopWatch.stop();
			
			//stopWatch.stop(); 실행 후 처리 업무 로직을 작성을 해야 함...(********* 필수)
			//조회 이력을 적제를 한다.
			
		}

	}
}
