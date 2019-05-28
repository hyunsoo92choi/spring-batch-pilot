package com.eBay.batch.common.step.support;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;

/**
 * <pre>
 * com.eBay.batch.common.step.support_ AbstractStep.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public class AbstractStep implements StepExecutionListener {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private StepExecution stepExecution;
	
	public StepExecution getStepExecution() {
		return this.stepExecution;
	}
	
	public ExecutionContext getExecutionContext() { 
		return this.stepExecution.getJobExecution().getExecutionContext(); 
	}
	
	public JobParameters getJobParameters() {
		return this.stepExecution.getJobParameters();
	}
	
	public Map getJobParametersMap() {
		
		Map<String, JobParameter> jobParametersMap = this.stepExecution.getJobParameters().getParameters();
		Map<String, Object> resultMap = new HashMap();

		for (Map.Entry<String, JobParameter> entry : jobParametersMap.entrySet()) {
			resultMap.put((String)entry.getKey(), ((JobParameter)entry.getValue()).getValue());
		}
		
		return resultMap;
	}
	
	public JobExecution getJobExecution() { 
		return this.stepExecution.getJobExecution(); 
	}
	
	public JobInstance getJobInstance() {
		return this.stepExecution.getJobExecution().getJobInstance();
	}
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}

}
