package com.divanxan.internetshop.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"com.divanxan.internetshop.dto"})
@EnableTransactionManagement
public class HibernateConfig {

    private final static String DATABASE_URL = "jdbc:mysql://localhost:3306/ishop?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private final static String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private final static String DATABASE_DIALECT = "org.hibernate.dialect.MySQL5Dialect";
    private final static String DATABASE_USERNAME = "root";
    private final static String DATABASE_PASSWORD = "root";

// имя бина используется в spring-security.xml
    @Bean("dataSource")
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        //Вносим информацию о соединении с БД
        dataSource.setDriverClassName(DATABASE_DRIVER);
        dataSource.setUrl(DATABASE_URL);
        dataSource.setUsername(DATABASE_USERNAME);
        dataSource.setPassword(DATABASE_PASSWORD);

        return dataSource;
    }

    @Bean
    public SessionFactory getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);

        builder.addProperties(getHibernateProperties());
        builder.scanPackages("com.divanxan.internetshop.dto");

        return builder.buildSessionFactory();
    }

    //Все настройки хибернета будут возвращены в этом методе
    private Properties getHibernateProperties() {

        Properties properties = new Properties();

        properties.put("hibernate.dialect", DATABASE_DIALECT);
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");

        //properties.put("hibernate.hbm2ddl.auto", "create");

        return  properties;
    }

    //TransactionManager bean
    @Bean
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }


}
