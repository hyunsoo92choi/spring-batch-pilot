package com.eBay.batch.app.goods.testbatch.controller;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eBay.batch.utils.JobLaunchHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * com.eBay.batch.app.goods.testbatch.controller_ GoodsTestJobController.java
 * </pre>
 * @date : 2019. 5. 28. 
 * @author : hychoi
 */
@Slf4j
@RestController
@RequestMapping("/v1/job/goods")
public class GoodsTestJobController {
	
	@Autowired
	private JobLaunchHelper jobLaunchHelper;

	@Autowired
	private Job goodsTestJob;
	
	// step 1
		@RequestMapping("/goodsTestJob")
		public String testJobs() {
			JobParameters jobParams = new JobParametersBuilder().addDate("DATE", new Date(System.currentTimeMillis()))
					.addString("goodsNo", "1000000959").toJobParameters();
			log.warn("############ goodsTestJob start #############");

			// jobName, job, parameter
			// job�� batch > GoodsTestJob.xml
			return jobLaunchHelper.run("goodsTestJob", goodsTestJob, jobParams);
		}

}
