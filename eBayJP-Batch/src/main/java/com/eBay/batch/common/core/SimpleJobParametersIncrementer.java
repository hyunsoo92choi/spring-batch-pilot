package com.eBay.batch.common.core;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

/**
 * <pre>
 * com.eBay.batch.common.core_ SimpleJobParametersIncrementer.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public class SimpleJobParametersIncrementer implements JobParametersIncrementer {
	
	public static final String BATCH_RUN_TOKEN = "batch.run.token";
	
	public JobParameters getNext(JobParameters jobParameters) {

		String token = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + RandomStringUtils.randomAlphanumeric(10);

		if ((jobParameters == null) || (jobParameters.isEmpty())) {
			return new JobParametersBuilder().addString("batch.run.token", token).toJobParameters();
		}

		return new JobParametersBuilder(jobParameters).addString("batch.run.token", token).toJobParameters();

	}

}
