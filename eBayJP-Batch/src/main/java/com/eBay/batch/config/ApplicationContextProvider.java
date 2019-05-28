package com.eBay.batch.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 * com.eBay.batch.config_ ApplicationContextProvider.java
 * </pre>
 * @date : 2019. 5. 28. 
 * @author : hychoi
 */
@Configuration
public class ApplicationContextProvider implements ApplicationContextAware {

	private static ApplicationContext ctx = null;
	 
    public static ApplicationContext getApplicationContext() {
        return ctx;
    }
    
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextProvider.ctx = applicationContext;
	}
	
	public static <T> T getBeanClass(Class<T> cls){
    	return ctx.getBean(cls);
    }
        
    public static Object getBeanObject(String name) {
    	return ctx.getBean(name);
    }
}
