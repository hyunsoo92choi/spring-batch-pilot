package com.eBay.batch.common.wrapper;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.eBay.batch.exception.SystemException;

/**
 * <pre>
 * com.eBay.batch.common.wrapper_ ApplicationContextWrapper.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
@Component
public final class ApplicationContextWrapper implements ApplicationContextAware {

	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		setContext(applicationContext);
	}
	
	private static void setContext(ApplicationContext ctx) {
		synchronized(ApplicationContextWrapper.class) {
			context = ctx;
		}
    }
	
	public static final <T> T getBean(Class<T> clazz) {
		if(clazz == null) {
			throw new SystemException("class cannot be null");
		}

        return context.getBean(clazz);
    }

	public static final Object getBean(String beanName) {
		if(StringUtils.isEmpty(beanName)) {
			throw new SystemException("beanName cannot be null");
		}

        return context.getBean(beanName);
    }

	public static final ApplicationContext getApplicationContext() {
        return context;
    }

}
