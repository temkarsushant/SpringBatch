package com.springbatch.SpringBatch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class MyJobListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("Started Date And Time: " + jobExecution.getStartTime());
		System.out.println(jobExecution.getStatus());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("Started Date And Time: " + jobExecution.getEndTime());
		System.out.println(jobExecution.getStatus());
	}

}
