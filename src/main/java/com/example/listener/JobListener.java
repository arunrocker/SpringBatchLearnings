package com.example.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("Before Job");
		System.out.println(jobExecution.getJobInstance().getJobName());
		System.out.println(jobExecution.getJobParameters());
		System.out.println(jobExecution.getExecutionContext());
		jobExecution.getExecutionContext().put("name", "arunrockzz");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("After Job");
		System.out.println(jobExecution.getJobInstance().getJobName());
		System.out.println(jobExecution.getJobParameters());
		System.out.println(jobExecution.getExecutionContext());
		
	}

}
