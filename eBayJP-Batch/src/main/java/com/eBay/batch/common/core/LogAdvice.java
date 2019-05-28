package com.eBay.batch.common.core;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * com.eBay.batch.common.core_ LogAdvice.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public class LogAdvice {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public void doBasicLogging(JoinPoint pjp) throws Throwable {
		
		Object[] args = pjp.getArgs();
		StringBuffer output = new StringBuffer();
		
		output.append(pjp.getTarget().getClass().getName()).append(": ");
		output.append(pjp.toShortString()).append(": ");
		
		Object[] arrayOfObject1;
		
		for (int i = 0; i < (arrayOfObject1 = args).length; i++) { 
			Object arg = arrayOfObject1[i];
			output.append(arg).append(" ");
		}
		
		this.log.info("Basic: " + output.toString());
	}
	
	public void doStronglyTypedLogging(Object item) {
		this.log.info("Processed: " + item);
	}
}
