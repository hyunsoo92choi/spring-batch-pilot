package com.eBay.batch.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.util.StringUtils;

/**
 * <pre>
 * com.eBay.batch.utils_ JobParametersUtil.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public class JobParametersUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(JobParametersUtil.class);
	
	public static boolean equalsJobParameters(JobParameters jobParam1, JobParameters jobParam2) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("jobParam1:{}, jobParam2:{}", jobParam1, jobParam2);
			if (jobParam1 != null) {
				logger.debug("jobParam1.getParameters():{}", jobParam1.getParameters());
			}
			if (jobParam2 != null) {
				logger.debug("jobParam2.getParameters():{}", jobParam2.getParameters());
			}
		}
		
		if ((jobParam1 == null) || (jobParam2 == null) || (jobParam1.getParameters() == null) || (jobParam2.getParameters() == null)) {
			return false;
		}
		Map<String, JobParameter> paramMap1 = jobParam1.getParameters();
		Map<String, JobParameter> paramMap2 = jobParam2.getParameters();
		
		
		int size1 = paramMap1.size();
		if (paramMap1.containsKey("batch.run.token")) {
			size1--;
		}
		int size2 = paramMap2.size();
		
		if (paramMap2.containsKey("batch.run.token")) {
			size2--;
		}
		
		if (size1 != size2) {
			return false;
		}
		
		for (String key : jobParam1.getParameters().keySet()) {
			
			if ( (!"batch.run.token".equals(key) ) &&
			( !StringUtils.pathEquals(jobParam1.getString(key), jobParam2.getString(key)) ) ) {
				return false;
			}
		}
		
		return true;
	}
}
