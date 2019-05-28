package com.eBay.batch.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.util.StringUtils;

import com.eBay.batch.common.wrapper.ApplicationContextWrapper;
import com.eBay.batch.exception.SystemException;

/**
 * <pre>
 * com.eBay.batch.config_ EnvConfig.java
 * </pre>
 * @date : 2019. 5. 28. 
 * @author : hychoi
 */
public final class EnvConfig {

	private static final Logger logger = LoggerFactory.getLogger(EnvConfig.class);
	
	private Map<String, String> envMap;

	private EnvConfig() {
		if (logger.isDebugEnabled()) {
			logger.debug("created EnvConfig instance.");
		}

		Environment envEnvironment = ApplicationContextWrapper.getBean(Environment.class);
		if (envEnvironment == null) {
			throw new IllegalStateException("Environment not initialzied.");
		}

		this.envMap = this.getMapFromEnvironment(envEnvironment);
	}

	private static final class SingletonHelper {
		private SingletonHelper() {
			throw new IllegalStateException("SingletonHelper class not create");
		}

		public static final EnvConfig instance = new EnvConfig();
	}

	public static String getStatically(String key) {
		if(StringUtils.isEmpty(key)) {
			throw new SystemException("Config key cannot be either null or empty");
		}

		if (SingletonHelper.instance == null) {
			throw new IllegalStateException("EnvConfig not initialzied.");
		}

		return SingletonHelper.instance.envMap.get(key);
	}

	@SuppressWarnings("rawtypes")
	private Map<String, String> getMapFromEnvironment(Environment env) {
		Map<String, String> rv = new HashMap<>();
		Iterator<PropertySource<?>> its;

		its = ((AbstractEnvironment) env).getPropertySources().iterator();
		while (its.hasNext()) {
			PropertySource<?> propSrc = its.next();

			if (propSrc instanceof EnumerablePropertySource) {
				for (String propName : ((EnumerablePropertySource) propSrc).getPropertyNames()) {
					rv.put(propName, String.valueOf(propSrc.getProperty(propName)));
				}
			}
		}
		return rv;
	}
}
