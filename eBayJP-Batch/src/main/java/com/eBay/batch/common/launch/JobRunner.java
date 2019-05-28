package com.eBay.batch.common.launch;

import java.util.Map;
import java.util.Properties;

import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.InitializingBean;

/**
 * <pre>
 * com.eBay.batch.common.launch_ JobRunner.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public abstract interface JobRunner extends InitializingBean {
	
	public abstract JobExecution run(String paramString, Map paramMap);
	public abstract JobExecution run(String paramString, Properties paramProperties);
	public abstract JobExecution rerun(String paramString, Map paramMap);
	public abstract JobExecution rerun(String paramString, Properties paramProperties);
	public abstract void stop(long paramLong);
	public abstract void abort(long paramLong);
}
