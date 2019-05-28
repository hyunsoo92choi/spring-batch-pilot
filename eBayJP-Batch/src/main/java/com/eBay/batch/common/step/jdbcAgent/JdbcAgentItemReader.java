package com.eBay.batch.common.step.jdbcAgent;

import java.util.List;
import java.util.Map;

import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.InitializingBean;

import com.eBay.batch.common.step.support.AbstractItemStreamReader;
import com.eBay.batch.utils.AssertUtil;

/**
 * <pre>
 * com.eBay.batch.common.step.jdbcAgent_ JdbcAgentItemReader.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public class JdbcAgentItemReader <T> extends AbstractItemStreamReader<T> implements InitializingBean {
	
	private String queryId;
	private Map<String, Object> parameterValues;
	private boolean initialized;
	private List<Map> resultList;
	
	public JdbcAgentItemReader() {
		setName(getClass().getSimpleName());
	}
	
	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}
	
	public void setParameterValues(Map<String, Object> parameterValues) {
		this.parameterValues = parameterValues;
	}
	
	protected void doClose() throws Exception {
		this.resultList = null;
		this.initialized = false;
	}
	
	protected void doOpen() throws Exception {
		AssertUtil.state(!this.initialized, "Already Initialized. Close before re-opening");
		setMaxItemCount(this.resultList.size());
		this.initialized = true;
	}
	
	protected T doRead() throws Exception {
		return null;
	}
	
	public void afterPropertiesSet() throws Exception {
	
	}

	@Override
	protected void doExecute(StepExecution stepExecution) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
