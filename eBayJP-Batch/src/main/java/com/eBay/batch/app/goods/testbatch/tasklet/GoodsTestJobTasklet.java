package com.eBay.batch.app.goods.testbatch.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.eBay.batch.app.goods.testbatch.dto.GoodsTestJobDto;
import com.eBay.batch.app.goods.testbatch.service.GoodsTestJobService;
import com.eBay.batch.app.goods.testbatch.service.GoodsTestJobServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * com.eBay.batch.app.goods.testbatch.tasklet_ GoodsTestJobTasklet.java
 * </pre>
 * 
 * @date : 2019. 5. 27.
 * @author : hychoi
 */
@Slf4j
public class GoodsTestJobTasklet implements Tasklet {

	private static final Logger logger = LoggerFactory.getLogger(GoodsTestJobTasklet.class);
	
	@Autowired
	GoodsTestJobService goodsTestJobService;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		// TODO start GoodsTestJob

		StringBuilder sb = new StringBuilder();
		sb.append("\n############# GoodsTestJob Tasklet start ################");
		sb.append("\n# GoodsTestJobTasklet Excuted");
		sb.append("\n# job Name : " + chunkContext.getStepContext().getJobName());
		sb.append("\n# step Name : " + chunkContext.getStepContext().getStepName());
		sb.append("\n# job parameters : " + chunkContext.getStepContext().getJobParameters());
		sb.append("\n############# GoodsTestJob Tasklet end ################");;
		
		logger.warn(sb.toString());

		GoodsTestJobDto goodsTestJobDto = new GoodsTestJobDto();

		goodsTestJobDto.setGoodsNo(chunkContext.getStepContext().getJobParameters().get("goodsNo").toString());

		goodsTestJobService.getGoodsList(goodsTestJobDto);

		return RepeatStatus.FINISHED;
	}

}
