package com.example.itemWriter;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.example.model.StudentCsv;
import com.example.model.StudentJdbc;
import com.example.model.StudentJson;
import com.example.model.StudentXml;

@Component
public class FirstItemWriter implements ItemWriter<StudentJdbc>{

	@Override
	public void write(List<? extends StudentJdbc> items) throws Exception {
		System.out.println("Inside ItemWriter");
		items.stream().forEach(System.out::println);
		
	}

}
