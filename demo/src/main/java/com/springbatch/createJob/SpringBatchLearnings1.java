package com.springbatch.createJob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBatchLearnings1 {
	@Autowired
	private JobBuilderFactory  jobs;
	@Autowired
	private StepBuilderFactory steps;
	
	@Bean
	public Job firstJob() {
		return jobs.get("first Job").start(firstStep()).build();
	}
	
	private Step firstStep() {
		return steps.get("first Step").tasklet(firstTask()).build();
	}
	
	private Tasklet firstTask() {
		return new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				// TODO Auto-generated method stub
				System.out.println("First Task Has been Exuted");
				return RepeatStatus.FINISHED;
			}
		};
		
		
	}
	

}
