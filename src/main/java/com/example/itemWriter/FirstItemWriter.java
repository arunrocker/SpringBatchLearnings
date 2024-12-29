package com.example.itemWriter;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class FirstItemWriter implements ItemWriter<Long>{

	@Override
	public void write(List<? extends Long> items) throws Exception {
		System.out.println("Inside ItemWriter");
		items.stream().forEach(i -> System.out.println(i));
		
	}

}
