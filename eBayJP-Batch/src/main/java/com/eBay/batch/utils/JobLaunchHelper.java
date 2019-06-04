package com.eBay.batch.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eBay.batch.common.batchlog.BatchLog;
import com.eBay.batch.common.batchlog.BatchLogStorage;
import com.eBay.batch.common.consts.BatchConst;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JobLaunchHelper {
	@Autowired 
	private JobLauncher jobLauncher;
	@Autowired 
	private BatchLogStorage batchLogStorage;
	
	private static final String resCodeNm = "code";
	private static final String resMessageNm = "message";
	private static final String successDefaultMsg = null;
	private static final String successDefaultCode = "0";
	private static final String knownErrorCode = "1";
	private static final String unKnownErrorCode = "1";

	public String run(String jobName, Job job, JobParameters jobParams) {
		String resCode = successDefaultCode; // 
		String resMessage = successDefaultMsg; // 

		log.debug(">>>>>" + jobName + " Start");

		try {
			JobExecution je = jobLauncher.run(job, jobParams);

			if (batchLogStorage.containsKey(jobName)) {
				
				BatchLog batchLog = batchLogStorage.batchLog(jobName);
				
				if (batchLog.getShowResult()) {
				    batchLog.add("==================================================================");
				    batchLog.add("  total:" + batchLog.getTotalCnt() + ",error:" + batchLog.getErrorCnt());
                }

				if (je.getStatus() != BatchStatus.COMPLETED) {
					resCode = knownErrorCode;
					resMessage = batchLog.toString() + BatchConst.BATCH_LOG_SEPERATOR + je.getAllFailureExceptions().toString();
				} else if (batchLog.isHasError()) {
					resCode = knownErrorCode;
					resMessage = batchLog.toString();
				} else {
					resCode = successDefaultCode;
					resMessage = batchLog.toString();
				}
			} else {
				if (je.getStatus() != BatchStatus.COMPLETED) {
					resCode = knownErrorCode;
					resMessage = je.getAllFailureExceptions().toString();
				}
			}
		} catch (Exception e) {
			resCode = unKnownErrorCode;
			resMessage = e.getMessage();
			log.error(">>>>>" + jobName + " Error", e);
		}

		log.debug(">>>>>" + jobName + " End");

		Map<String, Object> result = new HashMap<>();
		result.put(resCodeNm, resCode);
		result.put(resMessageNm, resMessage);

		return JsonUtils.toJson(result);
	}
}
