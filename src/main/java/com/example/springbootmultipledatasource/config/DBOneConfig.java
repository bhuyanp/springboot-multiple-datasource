package com.example.springbootmultipledatasource.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.springbootmultipledatasource.db1",
        entityManagerFactoryRef = "db1EntityManagerFactory",
        transactionManagerRef = "db1TransactionManager"
)
public class DBOneConfig {


    @Bean
    @ConfigurationProperties("spring.datasource.db1.hikari")
    public DataSourceProperties db1DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    //Unfortunately, to get EntityManagerFactoryBuilder injected,
    // we need to declare one of the data sources as @Primary.
    public DataSource db1Datasource() {
        return db1DataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }


    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory(
            @Qualifier("db1Datasource") DataSource db1Datasource,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(db1Datasource)
                .packages("com.example.springbootmultipledatasource.db1")
                .build();
    }

    @Bean
    public PlatformTransactionManager db1TransactionManager(
            @Qualifier("db1EntityManagerFactory") LocalContainerEntityManagerFactoryBean db1EntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(db1EntityManagerFactory.getObject()));
    }

}
