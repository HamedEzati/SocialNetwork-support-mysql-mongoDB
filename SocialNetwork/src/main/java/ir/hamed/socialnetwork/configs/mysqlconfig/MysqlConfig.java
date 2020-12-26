package ir.hamed.socialnetwork.configs.mysqlconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ConditionalOnProperty(name = "mysqldb")
public class MysqlConfig {
    @Value("${mysqlusername}")
    private String mysqlusername;
    @Value("${mysqlpassword}")
    private String mysqlpassword;
    @Value("${mysqlurl}")
    private String mysqlurl;
    @Value("${mysqldb}")
    private boolean mysqldb;
    @Value("${mongodb}")
    private boolean mongodb;

    @Bean
    public void config(){
        if (mysqldb){
            System.out.println("=============start config mysql==============");

        }
    }
//    @Bean
//    public DataSource dataSource() {
//
//            DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
//            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//            dataSource.setUsername(mysqlusername);
//            dataSource.setPassword(mysqlpassword);
//            dataSource.setUrl(mysqlurl);
//            return dataSource;
//
//    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "ir.hamed" });

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(mysqlurl);
        dataSource.setUsername( mysqlusername );
        dataSource.setPassword( mysqlpassword );
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("generate-ddl","true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");

        return properties;
    }




}
