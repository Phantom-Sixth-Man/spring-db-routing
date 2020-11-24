package com.optum.poc.springdbrouting.config;


import com.optum.poc.springdbrouting.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.optum.poc.springdbrouting.repository",
        transactionManagerRef = "tranasactionManager",
        entityManagerFactoryRef = "entityManager"
)
@EnableTransactionManagement
public class DataSourceConfig {

    @Autowired
    private SqlDevConfig sqlDevConfig;

    @Autowired
    private SqlTestConfig sqlTestConfig;

    @Bean
    @Primary
    @Autowired
    public DataSource dataSource() {
        DataSourceRouting routingDataSource = new DataSourceRouting();
        routingDataSource.initDataSource(devDataSource(),testDataSource());
        return routingDataSource;
    }

    public DataSource devDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(sqlDevConfig.getDriverClassName());
        dataSource.setUrl(sqlDevConfig.getUrl());
        dataSource.setUsername(sqlDevConfig.getUsername());
        dataSource.setPassword(sqlDevConfig.getPassword());
        return dataSource;
    }

    public DataSource testDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(sqlTestConfig.getDriverClassName());
        dataSource.setUrl(sqlTestConfig.getUrl());
        dataSource.setUsername(sqlTestConfig.getUsername());
        dataSource.setPassword(sqlTestConfig.getPassword());
        return dataSource;
    }

    @Bean(name = "entityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource()).packages("com.optum.poc.springdbrouting.model").build();
    }

    @Bean(name = "tranasactionManager")
    public JpaTransactionManager transactionManager(@Autowired @Qualifier("entityManager") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }
}
