package com.eBay.batch.common.step.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.support.ArgumentConvertingMethodInvoker;

/**
 * <pre>
 * com.eBay.batch.common.step.tasklet_ MethodInvokingTasklet.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public class MethodInvokingTasklet extends ArgumentConvertingMethodInvoker implements Tasklet, InitializingBean {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	protected ExitStatus mapResult(Object result) {
		if ((result instanceof ExitStatus)) {
			return (ExitStatus)result;
		}
		return ExitStatus.COMPLETED;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		Object[] arrayOfObject;

		for (int i = 0; i < (arrayOfObject = getArguments()).length; i++) { 
			Object arg = arrayOfObject[i];
			this.logger.debug("argument list:" + arg + "[" + arg.getClass() + "]");
		}
		
		prepare();
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		contribution.setExitStatus(mapResult(invoke()));
		
		return RepeatStatus.FINISHED;
	}

}
