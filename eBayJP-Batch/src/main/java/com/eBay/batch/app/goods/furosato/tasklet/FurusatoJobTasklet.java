package com.eBay.batch.app.goods.furosato.tasklet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.eBay.batch.app.goods.furosato.dto.JbtCsvDto;
import com.eBay.batch.utils.CsvFileReaderUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * com.eBay.batch.app.goods.furosato.tasklet_FurusatoJobTasklet.java
 * </pre>
 * @date : 2019. 6. 4. 오후 4:21:16
 * @author : hychoi
 */
@Slf4j
public class FurusatoJobTasklet implements Tasklet {
	
	private static final Logger logger = LoggerFactory.getLogger(FurusatoJobTasklet.class);
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		try {
			
			StringBuilder sb = new StringBuilder();
			sb.append("\n############# FurusatoJob Tasklet start ################");
			sb.append("\n# FurusatoJobTasklet Excuted");
			sb.append("\n# job Name : " + chunkContext.getStepContext().getJobName());
			sb.append("\n# step Name : " + chunkContext.getStepContext().getStepName());
			sb.append("\n# job parameters : " + chunkContext.getStepContext().getJobParameters());
			sb.append("\n##########################################################");
			
			logger.info(sb.toString());
			
			String csvFile = "C:\\Users\\hychoi\\Documents\\Yahoo.csv";
			
			Map<String, String> map = CsvFileReaderUtil.csvFileRead("C:\\Users\\hychoi\\Documents\\Yahoo.csv");
			String input = map.get("input").toString();
			Integer headerCnt = Integer.valueOf(map.get("headerSize"));
			List<String[]> records = CsvFileReaderUtil.csvFileParse(input,',', '"', headerCnt);
			List<JbtCsvDto> result = new ArrayList<JbtCsvDto>();
			
			for (String[] strings : records) {
				result.add(new JbtCsvDto(strings));
			}
			
		} catch (Exception e) {
			String alramTitle = "GoodsTestJobTasklet [실패]";
			log.error("[Goods]:>>>>> " +  alramTitle + ":", e);
			throw new Exception(e);
		}

		return RepeatStatus.FINISHED;
	}

}
