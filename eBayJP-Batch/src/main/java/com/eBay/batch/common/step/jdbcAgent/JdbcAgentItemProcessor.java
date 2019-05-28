package com.eBay.batch.common.step.jdbcAgent;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ItemProcessor;

import com.eBay.batch.utils.AssertUtil;

/**
 * <pre>
 * com.eBay.batch.common.step.jdbcAgent_ JdbcAgentItemProcessor.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public class JdbcAgentItemProcessor <I, O> extends AbstractJdbcAgentStep implements ItemProcessor<I, O> {

	
	protected String queryId;

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}
	
	public O process(I item) throws Exception {
		AssertUtil.notNull(this.queryId, "query id must not null");
		
		return null;
	}

	@Override
	protected void doExecute(StepExecution stepExecution) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
