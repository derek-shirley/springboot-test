package derek.shirley.springboot.test;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.WebApplicationInitializer;

/**
 * The main entry point and config for the app.
 *
 * @since 1.0.0
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {H2ConsoleAutoConfiguration.class})
public class ApplicationConfig extends SpringBootServletInitializer implements WebApplicationInitializer {

    /**
     * Main entry point for the spring boot app.
     *
     * @param args args
     */
    public static void main(String[] args) {

        SpringApplication.run(ApplicationConfig.class, args);

    }

    /**
     * Test transaction manager.
     *
     * @param dataSource to be wired in
     * @return <code>DataSourceTransactionManager</code>
     */
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}