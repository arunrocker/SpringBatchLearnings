package com.example.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
public class SecondJobScheduling {
	@Autowired
	JobLauncher jobLauncher;
	@Qualifier("secondJob")
	@Autowired
	Job secondJob;
	//@Scheduled(cron="0 0/1 * 1/1 * ?")
	public void secondJobScheduling() {
		Map<String, JobParameter> jobParam = new HashMap<>();
		jobParam.put("currentTime", new JobParameter(System.currentTimeMillis()));
		JobParameters jobParams = new JobParameters(jobParam);
		try {
			JobExecution jobExecution = 
					jobLauncher.run(secondJob, jobParams);
			System.out.println(jobExecution.getId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Exception in running a job");
		}
	}

}
