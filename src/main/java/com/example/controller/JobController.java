package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.request.Request;
import com.example.service.JobService;

@RestController
@RequestMapping("/api/job")
public class JobController {
	@Autowired
	private JobService jobService;
	@Autowired
	private JobOperator jobOperator;
	@GetMapping("/start/{jobName}")
	public String launchJob(@PathVariable String jobName,@RequestBody List<Request> paramValues) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		jobService.runJob(jobName,paramValues);
		return "Job Launched....";
	}
	@GetMapping("/stop/{executionId}")
	public String stopJob(@PathVariable Long executionId) {
		try {
			jobOperator.stop(executionId);
		} catch (NoSuchJobExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobExecutionNotRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Job Stopped...";
	}
}
