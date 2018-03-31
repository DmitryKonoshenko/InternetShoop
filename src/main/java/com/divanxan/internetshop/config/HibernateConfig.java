package com.divanxan.internetshop.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Hibernate configuration class
 *
 * @version 1.0
 * @autor Dmitry Konoshenko
 * @since version 1.0
 */
@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = {"com.divanxan.internetshop"})
@EnableTransactionManagement
public class HibernateConfig {

    @Autowired
    private Environment env;

    // имя бина используется в spring-security.xml

    /**
     * Configuration DataSource method
     *
     * @return DataSource with properties data source connection
     */
    @Bean("dataSource")
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        //Вносим информацию о соединении с БД
        dataSource.setDriverClassName(env.getProperty("mysql.driver"));
        dataSource.setUrl(env.getProperty("mysql.jdbcUrl"));
        dataSource.setUsername(env.getProperty("mysql.username"));
        dataSource.setPassword(env.getProperty("mysql.password"));

        return dataSource;
    }

    /**
     * SessionFactory creation method
     *
     * @param  dataSource
     * @return SessionFactory
     */
    @Bean
    public SessionFactory getSessionFactory(DataSource dataSource) {

        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);

        builder.addProperties(getHibernateProperties());
        builder.scanPackages("com.divanxan.internetshop");

        SessionFactory sessionFactory =  builder.buildSessionFactory();

        return sessionFactory;
    }

    /**
     * Method return Hibernate properties
     *
     * @return Properties properties
     */
    //Все настройки хибернета будут возвращены в этом методе
    private Properties getHibernateProperties() {

        Properties properties = new Properties();

        properties.put("hibernate.dialect", env.getProperty("mysql.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("hiber.show.sql"));
        properties.put("hibernate.format_sql", env.getProperty("hiber.format.sql"));

//        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hiber.hbm2ddl"));

        return properties;
    }

    /**
     * TransactionManager bean
     *
     * @param sessionFactory
     * @return HibernateTransactionManager
     */
    @Bean
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }


}
