package com.eBay.batch.common.core;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.batch.core.configuration.DuplicateJobException;
import org.springframework.batch.core.configuration.support.ApplicationContextFactory;
import org.springframework.batch.core.configuration.support.JobLoader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.Lifecycle;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.util.Assert;

/**
 * <pre>
 * com.eBay.batch.common.core_ AutomaticJobRegister.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public class AutomaticJobRegister implements Ordered, Lifecycle, ApplicationListener, ApplicationContextAware, InitializingBean  {

	private Collection<ApplicationContextFactory> applicationContextFactories = new ArrayList();
	private JobLoader jobLoader;
	private ApplicationContext applicationContext;
	private volatile boolean running = false;
	private Object lifecycleMonitor = new Object();
	private int order = Integer.MAX_VALUE;
	
	public void afterPropertiesSet() throws Exception {
		Assert.state(this.jobLoader != null, "A JobLoader must be provided");
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void onApplicationEvent(ApplicationEvent event) {
		
		if ( event.getSource() == this.applicationContext ) {
			if ( (event instanceof ContextRefreshedEvent) ) {
				start();
			} else if ( (event instanceof ContextClosedEvent) ) {
				stop();
			}
		}
	}

	public void start() {
		
		synchronized (this.lifecycleMonitor) {
			if (this.running) {
				return;
			}
			for (ApplicationContextFactory factory : this.applicationContextFactories) {
				try {
					this.jobLoader.load(factory);
				} catch (DuplicateJobException e) {
					throw new IllegalStateException(e);
				}
			}
			this.running = true;
		}
	}

	public void stop() {
		
		synchronized (this.lifecycleMonitor) {
			this.jobLoader.clear();
			this.running = false;
		}
	}

	public boolean isRunning() {
		return true;
	}

	public int getOrder() {
		return this.order;
	}
	
	public void setApplicationContextFactories(ApplicationContextFactory[] applicationContextFactories) {
		ApplicationContextFactory[] arrayOfApplicationContextFactory;

		int j = (arrayOfApplicationContextFactory = applicationContextFactories).length; 
		for (int i = 0; i < j; i++) { 
			ApplicationContextFactory applicationContextFactory = arrayOfApplicationContextFactory[i];
			this.applicationContextFactories.add(applicationContextFactory);
		}
	}
	
	public void setJobLoader(JobLoader jobLoader) {
		this.jobLoader = jobLoader;
	}

	public void setOrder(int order) {
		this.order = order;
	}

}
