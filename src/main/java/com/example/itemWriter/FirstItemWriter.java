package com.example.itemWriter;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.example.model.StudentCsv;

@Component
public class FirstItemWriter implements ItemWriter<StudentCsv>{

	@Override
	public void write(List<? extends StudentCsv> items) throws Exception {
		System.out.println("Inside ItemWriter");
		items.stream().forEach(System.out::println);
		
	}

}
