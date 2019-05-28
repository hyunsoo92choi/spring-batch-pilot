package com.eBay.batch.common.listener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepListenerSupport;

/**
 * <pre>
 * com.eBay.batch.common.listener_ DefaultStepListener.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public class DefaultStepListener <T, S> extends StepListenerSupport <T, S> {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private StepExecution stepExecution;

	public ExitStatus afterStep(StepExecution stepExecution) {
		
		if (this.logger.isDebugEnabled()) {
			
			StringBuffer sb = new StringBuffer();
			
			sb.append("@@@step completed : ");
			sb.append("@@@JobName:");
			sb.append(stepExecution.getJobExecution().getJobInstance().getJobName());
			sb.append(stepExecution.getSummary());
			
			this.logger.debug(sb.toString());
		}
		
		return stepExecution.getExitStatus();
	}

	public void beforeStep(StepExecution stepExecution) {
		
		if (this.logger.isDebugEnabled()) {
		
			StringBuffer sb = new StringBuffer();
			
			sb.append("@@@step is going to be started : ");
			sb.append("@@@JobName:").append(stepExecution.getJobExecution().getJobInstance().getJobName());
			sb.append(",StepName:").append(stepExecution.getStepName());
			sb.append(",JobParameters:").append(stepExecution.getJobParameters());
			
			this.logger.debug(sb.toString());
		}
		
		this.stepExecution = stepExecution;
	}

	public void afterWrite(List items) {

		if ((this.logger.isDebugEnabled()) && (items != null)) {

			StringBuffer sb = new StringBuffer();

			sb.append("after write items : ");
			sb.append("JobName:").append(this.stepExecution.getJobExecution().getJobInstance().getJobName());
			sb.append(",StepName:").append(this.stepExecution.getStepName());
			sb.append(",written item count : ").append(items.size());

			this.logger.debug(sb.toString());
		}
	}
}
