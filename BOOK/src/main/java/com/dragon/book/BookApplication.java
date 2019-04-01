package com.dragon.book;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling //定時任务注解
@SpringBootApplication
@MapperScan("com.dragon.book.mapper")
public class BookApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

}

