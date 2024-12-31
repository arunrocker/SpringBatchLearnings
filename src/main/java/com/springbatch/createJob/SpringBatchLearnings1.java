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
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.example.itemProcessor.FirstItemProcessor;
import com.example.itemReader.FirstItemReader;
import com.example.itemWriter.FirstItemWriter;
import com.example.model.StudentCsv;

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
	private FirstItemReader itemReader;
	@Autowired
	private FirstItemProcessor itemProcessor;
	@Autowired
	private FirstItemWriter itemWriter;
	
//	@Bean
	public Job firstJob() {
		return jobs.get("first Job")
				.incrementer(new RunIdIncrementer())
				.start(firstStep()).build();
	}
	@Bean
	public Job secondJob() {
		return jobs.get("Second Job")
				.incrementer(new RunIdIncrementer())
				.start(secondStep()).build();
	}
	
	private Step firstStep() {
		return steps.get("first Step")
				.tasklet(firstTask())
				.build();
	}
	/*
	 * private Step secondStep() { return
	 * steps.get("Second Step").tasklet(tasklet2).build(); 
	 * }
	 */
	
	private Step secondStep() {
		return steps.get("Second Step").<StudentCsv,StudentCsv>chunk(3)
				.reader(flatFileItemReader())//.processor(itemProcessor)
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

	private FlatFileItemReader<StudentCsv> flatFileItemReader() {
	    FlatFileItemReader<StudentCsv> flatFileItemReader = new FlatFileItemReader<>();
	    flatFileItemReader.setResource(new FileSystemResource(
	            "C:\\Users\\Priya s\\Documents\\workspace-spring-tool-suite-4-4.27.0.RELEASE\\demo\\inputFolder\\students.csv"));
	    
	    // Create a DefaultLineMapper and configure it
	    DefaultLineMapper<StudentCsv> lineMapper = new DefaultLineMapper<>();
	    
	    // Set up the tokenizer for CSV parsing
	    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();//new DelimitedLineTokenizer("|");
	    lineTokenizer.setNames("ID", "First Name", "Last Name", "Email");
	    lineTokenizer.setDelimiter("|");
	    
	    // Set the tokenizer
	    lineMapper.setLineTokenizer(lineTokenizer);
	    
	    // Create and set up the FieldSetMapper (BeanWrapperFieldSetMapper)
	    BeanWrapperFieldSetMapper<StudentCsv> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
	    fieldSetMapper.setTargetType(StudentCsv.class); // Ensure that StudentCsv is your domain model
	    
	    lineMapper.setFieldSetMapper(fieldSetMapper);
	    
	    // Set the LineMapper to the reader
	    flatFileItemReader.setLineMapper(lineMapper);
	    flatFileItemReader.setLinesToSkip(1);

	    return flatFileItemReader;
	}

	

}
