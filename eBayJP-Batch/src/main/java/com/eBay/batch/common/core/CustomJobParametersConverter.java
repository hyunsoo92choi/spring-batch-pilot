package com.eBay.batch.common.core;

import java.util.Map;
import java.util.Properties;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.converter.DefaultJobParametersConverter;

/**
 * <pre>
 * com.eBay.batch.common.core_ CustomJobParametersConverter.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public class CustomJobParametersConverter extends DefaultJobParametersConverter {
	
	public JobParameters getJobParameters(Map map) {
		
		if ((map == null) || (map.isEmpty())) {
			return new JobParameters();
		}

		Properties prop = new Properties();
		prop.putAll(map);

		return	getJobParameters(prop);
	}
}
