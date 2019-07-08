package com.eBay.batch.app.goods.furosato.controller;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eBay.batch.app.goods.testbatch.dto.RequestGoodsTestJobDto;
import com.eBay.batch.utils.JobLaunchHelper;

/**
 * <pre>
 * com.eBay.batch.app.goods.furosato.controller_JtbBatchJobController.java
 * </pre>
 * @date : 2019. 6. 4. 오후 2:38:16
 * @author : hychoi
 */
@RestController
@RequestMapping("/v1/job/furusato")
public class JtbBatchJobController {
	
	@Autowired
	private JobLaunchHelper jobLaunchHelper;
	
	@Autowired
	private Job furusatoJob;
	
	@RequestMapping(value = "/furusatoJob" , method = RequestMethod.GET)
	public String testJobs() {
		
		JobParametersBuilder jobParams = new JobParametersBuilder();
		
		jobParams.addDate("DATE", new Date(System.currentTimeMillis()));
	
		return jobLaunchHelper.run("furusatoJob", furusatoJob, jobParams.toJobParameters());
	}

}
