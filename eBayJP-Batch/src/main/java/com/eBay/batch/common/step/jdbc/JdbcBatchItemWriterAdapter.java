package com.eBay.batch.common.step.jdbc;

import java.util.List;

import org.springframework.batch.item.database.JdbcBatchItemWriter;

/**
 * <pre>
 * com.eBay.batch.common.step.jdbc_ JdbcBatchItemWriterAdapter.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public class JdbcBatchItemWriterAdapter<T> extends JdbcBatchItemWriter<T> {

	private String query;
	
	public void setSql(String sql) {
		this.query = sql;
		super.setSql(sql);
	}

	public void write(List<? extends T> items) throws Exception {
		
		if (logger.isDebugEnabled()) {
			logger.debug("execute batch sql [\n" + this.query + "\n] with items(" + items.size() + ")");
		}
		
		try {
			super.write(items);
		} 
		catch (org.springframework.dao.EmptyResultDataAccessException e) {
			logger.info("EmptyResultDataAccessException throws, but ignore it", e);
		}
	}
}
