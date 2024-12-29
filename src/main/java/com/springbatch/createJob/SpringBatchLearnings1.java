package com.springbatch.createJob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.itemProcessor.FirstItemProcessor;
import com.example.itemReader.FirstItemReader;
import com.example.itemWriter.FirstItemWriter;
import com.example.listener.JobListener;
import com.tasklet.customTasklet.CustomTaskLet;

@Configuration
public class SpringBatchLearnings1 {
	@Autowired
	private JobBuilderFactory  jobs;
	@Autowired
	private StepBuilderFactory steps;
	/*
	 * @Autowired 
	 * private CustomTaskLet tasklet2;
	 */
	@Autowired
	private JobListener jobListener;
	@Autowired
	private StepExecutionListener firstStepListener;
	@Autowired
	private FirstItemReader itemReader;
	@Autowired
	private FirstItemProcessor itemProcessor;
	@Autowired
	private FirstItemWriter itemWriter;
	
	@Bean
	public Job firstJob() {
		return jobs.get("first Job")
				.incrementer(new RunIdIncrementer())
				.start(firstStep()).next(secondStep())
				.listener(jobListener).build();
	}
	
	private Step firstStep() {
		return steps.get("first Step")
				.tasklet(firstTask()).listener(firstStepListener)
				.build();
	}
	/*
	 * private Step secondStep() { return
	 * steps.get("Second Step").tasklet(tasklet2).build(); 
	 * }
	 */
	
	private Step secondStep() {
		return steps.get("Second Step").<Integer,Long>chunk(3)
				.reader(itemReader).processor(itemProcessor)
				.writer(itemWriter).build();
		
	}
	private Tasklet firstTask() {
		return new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				// TODO Auto-generated method stub
				System.out.println("First Task Has been Exuted");
				System.out.println("Tasklet:"+chunkContext.getStepContext().getStepExecutionContext());
				return RepeatStatus.FINISHED;
			}
		};
		
		
	}
	

}
