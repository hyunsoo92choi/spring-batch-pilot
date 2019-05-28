package com.eBay.batch.system;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * com.eBay.batch.system_ SystemHealthController.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
@RestController
@Slf4j
public class SystemHealthController {
	
	@RequestMapping("/sys/healthCheck")
	public String healthCheck(){
		return "Hello System";
	}

	@RequestMapping("/sys/healthCheck2")
	public String healthCheck2(){
		for(int i=0 ; i< 10; i++) {
			log.info("running healthCheck2 {}",i);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "Hello System";
	}
}
