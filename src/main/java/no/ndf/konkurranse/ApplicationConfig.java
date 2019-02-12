package no.ndf.konkurranse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;

import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Created by gloken on 20.12.2015.
 */
@Configuration
public class ApplicationConfig {

    @Bean
    DataSource dataSource() {
        DataSource dataSource = null;
//        JndiTemplate jndi = new JndiTemplate();
//
//        try {
//            dataSource = (DataSource) jndi.lookup("java:comp/env/jdbc/MySQLDS");
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
        return dataSource;
    }
}
