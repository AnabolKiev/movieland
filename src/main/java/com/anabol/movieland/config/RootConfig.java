package com.anabol.movieland.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
@EnableScheduling
@EnableTransactionManagement
@ComponentScan(basePackages = "com.anabol.movieland",
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.anabol.movieland.web.*"))
public class RootConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yamlProperties = new YamlPropertiesFactoryBean();
        yamlProperties.setResources(new ClassPathResource("application.yml"));
        propertySourcesPlaceholderConfigurer.setProperties(yamlProperties.getObject());
        return propertySourcesPlaceholderConfigurer;
    }

    @Bean
    public BasicDataSource dataSource(@Value("${db.driver}") String driverClassName,
                                      @Value("${db.url}") String url,
                                      @Value("${db.username}") String username,
                                      @Value("${db.password}") String password,
                                      @Value("${db.maxTotal:20}") int maxTotal,
                                      @Value("${db.minIdle:2}") int minIdle,
                                      @Value("${db.maxIdle:10}") int maxIdle,
                                      @Value("${db.maxWaitMillis:10000}") long maxWaitMillis
    ) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxTotal(maxTotal);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxIdle(maxIdle);
        dataSource.setMaxWaitMillis(maxWaitMillis);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}