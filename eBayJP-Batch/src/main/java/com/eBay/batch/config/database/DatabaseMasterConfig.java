package com.eBay.batch.config.database;

import javax.sql.DataSource;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * com.eBay.batch.config.database_ DatabaseMasterConfig.java
 * </pre>
 * @date : 2019. 5. 28. 
 * @author : hychoi
 */
@MapperScan(annotationClass = Mapper.class, basePackages = "com.eBay", sqlSessionTemplateRef = "sqlSessionTemplateMaster")
@Configuration
@Slf4j
public class DatabaseMasterConfig {

	@Autowired
	private ConfigurableEnvironment environment;
	
	@Bean(name = "poolPropsMaster")
    @ConfigurationProperties(prefix = "batch.datasource.master")
    public PoolProperties getPoolProperties(){
        return new PoolProperties();
    }



	@Bean(name = "dataSourceMaster", destroyMethod = "close")
	public DataSource dataSourceDevMaster(@Qualifier("poolPropsMaster") PoolProperties property) {
		String decrytpassword = "";
        decrytpassword = property.getPassword();

        property.setPassword(decrytpassword);

        return new org.apache.tomcat.jdbc.pool.DataSource(property);
	}

	@Bean(name = "sqlSessionFactoryMaster")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSourceMaster") DataSource dataSource)
			throws Exception {

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setVfs(SpringBootVFS.class);

		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

		sqlSessionFactoryBean.setConfigLocation(
				resourcePatternResolver
						.getResource(environment.getProperty("batch.datasource.Master.config-location")));
		sqlSessionFactoryBean.setMapperLocations(
				resourcePatternResolver
						.getResources(environment.getProperty("batch.datasource.Master.mapper-locations")));

		return sqlSessionFactoryBean.getObject();
	}

	@Bean(name = "sqlSessionTemplateMaster", destroyMethod = "clearCache")
	public SqlSessionTemplate sqlSessionTemplate(
			@Qualifier("sqlSessionFactoryMaster") SqlSessionFactory sqlSessionFactory) throws Exception {
		log.info("sqlSessionFactoryMaster created");
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean(name = "txManagerMaster")
	public DataSourceTransactionManager transactionManager(
			@Qualifier("dataSourceMaster") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
}
