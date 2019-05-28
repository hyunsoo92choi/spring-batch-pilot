package com.eBay.batch.common.step.jdbcAgent;

import java.util.Map;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.eBay.batch.utils.AssertUtil;

/**
 * <pre>
 * com.eBay.batch.common.step.jdbcAgent_ JdbcAgentTasklet.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public class JdbcAgentTasklet extends AbstractJdbcAgentStep implements Tasklet {

	private String queryId;
	private Map<String, Object> parameterValues;
	
	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}
	
	public void setParameterValues(Map<String, Object> parameterValues) {
		this.parameterValues = parameterValues;
	}
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		AssertUtil.notNull(this.queryId, "queryId must not null");
		return RepeatStatus.FINISHED;
	}

	@Override
	protected void doExecute(StepExecution stepExecution) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
