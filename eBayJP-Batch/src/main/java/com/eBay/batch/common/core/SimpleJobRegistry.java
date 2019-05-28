package com.eBay.batch.common.core;

import java.util.List;
import java.util.Map;

import org.springframework.batch.core.configuration.JobRegistry;

/**
 * <pre>
 * com.eBay.batch.common.core_ SimpleJobRegistry.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public abstract interface SimpleJobRegistry extends JobRegistry {
	
	public abstract List<Map<String, String>> getJobInfoDataset();
	public abstract String getServerId();
	public abstract void setServerId(String paramString);
}
