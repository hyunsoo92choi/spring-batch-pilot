package com.eBay.batch.common.step.tasklet;

import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

/**
 * <pre>
 * com.eBay.batch.common.step.tasklet_ SimpleJdbcTemplateTasklet.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public class SimpleJdbcTemplateTasklet implements Tasklet, InitializingBean {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private JdbcTemplate simpleJdbcTemplate;
	
	private Map<String, Object> parameterValues;
	
	private String sql;
	
	public void setSql(String sql) {
		this.sql = sql;
	}
	
	public void setParameterValues(Map<String, Object> parameterValues) {
		this.parameterValues = parameterValues;
	}
	
	public void setDataSource(DataSource dataSource) {
		if (this.simpleJdbcTemplate == null) {
			this.simpleJdbcTemplate = new JdbcTemplate(dataSource);
		}
	}
	
	public void setSimpleJdbcTemplate(JdbcTemplate simpleJdbcTemplate) {
		this.simpleJdbcTemplate = simpleJdbcTemplate;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.simpleJdbcTemplate, "A DataSource or a SimpleJdbcTemplate is required.");
		Assert.notNull(this.sql, "An SQL statement is required.");		
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		int rows = this.simpleJdbcTemplate.update(this.sql, this.parameterValues);
		
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("SQL update affected " + rows + " rows");
		}
		return RepeatStatus.FINISHED;
	}

}
