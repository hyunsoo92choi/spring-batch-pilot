package com.eBay.batch.common.launch;
/**
 * <pre>
 * com.eBay.batch.common.launch_ JobRunnerAware.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public abstract interface JobRunnerAware {
	public abstract void setJobRunner(JobRunner paramJobRunner);
}
