package com.eBay.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.eBay")
public class EBayJpBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(EBayJpBatchApplication.class, args);
	}

}
