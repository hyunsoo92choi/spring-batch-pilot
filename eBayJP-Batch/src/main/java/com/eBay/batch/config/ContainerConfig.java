package com.eBay.batch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * <pre>
 * com.eBay.batch.config_ ContainerConfig.java
 * </pre>
 * @date : 2019. 5. 28. 
 * @author : hychoi
 */
@Configuration
@ImportResource({ "classpath*:/batch/**/*.xml" })
public class ContainerConfig {

}
