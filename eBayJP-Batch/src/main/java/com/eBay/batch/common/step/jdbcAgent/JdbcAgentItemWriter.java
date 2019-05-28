package com.eBay.batch.common.step.jdbcAgent;

import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ItemWriter;

/**
 * <pre>
 * com.eBay.batch.common.step.jdbcAgent_ JdbcAgentItemWriter.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public class JdbcAgentItemWriter<T> extends AbstractJdbcAgentStep implements ItemWriter<T> {

	protected String queryId;
	
	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}
	
	@Override
	public void write(List<? extends T> items) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doExecute(StepExecution stepExecution) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
