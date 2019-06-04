package com.eBay.batch.common.listener;

import java.util.Set;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.eBay.batch.common.service.alram.SlackAlramService;

import lombok.extern.slf4j.Slf4j;

//@Component
@Slf4j
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	@Autowired
    private JobOperator jobOperator;
	
	@Autowired 
	SlackAlramService slackService;
	
	@Value("${batcherror.beforeerror.receive}")
    private String receive;
	
	@Override
    public void beforeJob(JobExecution jobExecution) {
        
		Set<Long> runningExecution;
		String title = null;
		String alramContents = null;
        try {
            
        	final String jobName = jobExecution.getJobInstance().getJobName();
            final long instanceId = jobExecution.getJobInstance().getInstanceId();
            runningExecution = jobOperator.getRunningExecutions(jobName);

            // 동시에 실행중인 Job 확인
            if (!runningExecution.isEmpty() && 2 <= runningExecution.size()) {
                // 새로 실행된 Job 중지
                jobExecution.stop();
                log.error("{}-{} Job has been stoped!! because this job is already running!!!", jobName, instanceId);
//                final String title = String.format("%s Job has been stoped!!" , jobName);               
//                final String alramContents = String.format("%s-%d Job has been stoped!! because this job is already running!!!", jobName, instanceId);
                title = String.format("%s Job has been stoped!!" , jobName);
                alramContents = String.format("%s-%d Job has been stoped!! because this job is already running!!!", jobName, instanceId);

                // Slack 알람 발송
                slackService.alramSend(receive, title, alramContents, "Slack incoming webhook Call");
            }
            log.debug("beforeJob : {}-{}", jobName, instanceId);
            super.beforeJob(jobExecution);
        } catch (Exception e) {
            log.error("{}", e);
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        
    	try {
            
    		final String jobName = jobExecution.getJobInstance().getJobName();
            final long instanceId = jobExecution.getJobInstance().getInstanceId();
            final String exitCode = jobExecution.getExitStatus().getExitCode();

            // 정상종료, 강제중지 여부 확인
            if (jobExecution.getStatus() != BatchStatus.COMPLETED && jobExecution.getStatus() != BatchStatus.STOPPED) {
                log.error("{}-{} Job has been error!!! Exit Code is {}", jobName, instanceId, exitCode);
                final String title = String.format("%s Job has been stoped!!" , jobName);
                final String alramContents = String.format("%s-%d Job has been error!!! Exit Code is %s", jobName, instanceId, exitCode);
                // Slack 알람 발송
                slackService.alramSend(receive, title, alramContents, "Slack incoming webhook Call");
            }

            log.debug("afterJob : {}-{}", jobName, instanceId);

            super.afterJob(jobExecution);
        } catch (Exception e) {
            log.error("{}", e);
        }
    }
	
}
