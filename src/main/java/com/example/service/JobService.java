package com.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.request.Request;

@Service
public class JobService {
	@Autowired
	JobLauncher jobLauncher;
	@Qualifier("firstJob")
	@Autowired
	Job firstjob;
	@Qualifier("secondJob")
	@Autowired
	Job secondJob;

	@Async
	public void runJob(String jobName,List<Request> paramValues) {
		Map<String, JobParameter> jobParam = new HashMap<>();
		jobParam.put("currentTime", new JobParameter(System.currentTimeMillis()));
		paramValues.stream().forEach(param ->jobParam.put(param.getParamKey(),new JobParameter(param.getParamValue()))
		);
		JobParameters jobParams = new JobParameters(jobParam);
		try {
			JobExecution jobExecution = null;
			if (jobName.equals("firstJob")) {
				jobExecution = jobLauncher.run(firstjob, jobParams);
			} else if (jobName.equals("secondJob")) {
				jobExecution = jobLauncher.run(secondJob, jobParams);
			}
			System.out.println(jobExecution.getId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Exception in running a job");
		}
	}

}
