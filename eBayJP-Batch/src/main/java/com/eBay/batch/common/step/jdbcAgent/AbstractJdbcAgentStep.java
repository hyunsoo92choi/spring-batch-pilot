package com.eBay.batch.common.step.jdbcAgent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.step.AbstractStep;
import org.springframework.beans.factory.InitializingBean;

/**
 * <pre>
 * com.eBay.batch.common.step.jdbcAgent_ AbstractJdbcAgentStep.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public abstract class AbstractJdbcAgentStep extends AbstractStep implements InitializingBean {
	
private Logger logger = LoggerFactory.getLogger(getClass());
	
	public void afterPropertiesSet()throws Exception {}

}
