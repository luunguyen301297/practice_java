package org.example.jdbc.configuration;

import org.example.jdbc.dao.ModelDAO;
import org.example.jdbc.dao.ModelDAOImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/13/2024
 */
@Configuration
@ComponentScan(value = {"org.example.jdbc"})
public class DIConfiguration {

    @Bean
    @Scope("singleton")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:54320/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("admin@123");

        return dataSource;
    }

    @Bean
    public ModelDAO getContactDAO() {
        return new ModelDAOImpl(getDataSource());
    }

}