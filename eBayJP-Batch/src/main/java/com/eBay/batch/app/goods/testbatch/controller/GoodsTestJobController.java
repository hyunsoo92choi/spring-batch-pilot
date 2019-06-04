package com.eBay.batch.app.goods.testbatch.controller;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eBay.batch.app.goods.testbatch.dto.RequestGoodsTestJobDto;
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
	
	
	@RequestMapping(value = "/goodsTestJob" , method = RequestMethod.POST)
	public String testJobs(@RequestBody RequestGoodsTestJobDto reqGoodsTestJobDto) {
		
		JobParametersBuilder jobParams = new JobParametersBuilder();
		
		jobParams.addDate("DATE", new Date(System.currentTimeMillis()));
		jobParams.addString("goodsNo", reqGoodsTestJobDto.getGoodsNo());
		jobParams.addString("goodsNm", reqGoodsTestJobDto.getGoodsNm());
	
		return jobLaunchHelper.run("goodsTestJob", goodsTestJob, jobParams.toJobParameters());
	}

}
