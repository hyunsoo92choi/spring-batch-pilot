package com.eBay.batch.app.goods.testbatch.tasklet;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.eBay.batch.app.goods.testbatch.dto.GoodsTestJobDto;
import com.eBay.batch.app.goods.testbatch.dto.RequestGoodsTestJobDto;
import com.eBay.batch.app.goods.testbatch.service.GoodsTestJobService;

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
//	@Autowired SlackAlramService slackService;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		try {
			
			StringBuilder sb = new StringBuilder();
			sb.append("\n############# GoodsTestJob Tasklet start ################");
			sb.append("\n# GoodsTestJobTasklet Excuted");
			sb.append("\n# job Name : " + chunkContext.getStepContext().getJobName());
			sb.append("\n# step Name : " + chunkContext.getStepContext().getStepName());
			sb.append("\n# job parameters : " + chunkContext.getStepContext().getJobParameters());
			sb.append("\n##########################################################");
			;
			
			logger.info(sb.toString());

			List<GoodsTestJobDto> goodsTestJobDtoList = new ArrayList<GoodsTestJobDto>();
			RequestGoodsTestJobDto requestGoodsTestJobDto = new RequestGoodsTestJobDto();
			requestGoodsTestJobDto
					.setGoodsNo(chunkContext.getStepContext().getJobParameters().get("goodsNo").toString());
			
			requestGoodsTestJobDto
			.setGoodsNm(chunkContext.getStepContext().getJobParameters().get("goodsNm").toString());

			goodsTestJobDtoList = goodsTestJobService.getGoodsList(requestGoodsTestJobDto);
		} catch (Exception e) {
			String alramTitle = "GoodsTestJobTasklet [실패]";
			log.error("[Goods]:>>>>> " +  alramTitle + ":", e);
//			slackService.alramSend("batch-logging", alramTitle, e.getMessage(), e.getStackTrace().toString());
			throw new Exception(e);
		}

		return RepeatStatus.FINISHED;
	}

}
