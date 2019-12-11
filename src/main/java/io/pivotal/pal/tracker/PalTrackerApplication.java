package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;


@SpringBootApplication
public class PalTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(PalTrackerApplication.class, args);
    }

    @Bean
    @ConditionalOnBean(JdbcTimeEntryRepository.class)
    public JdbcTimeEntryRepository jdbcTimeEntryRepository(DataSource dataSource) {
        return new JdbcTimeEntryRepository(dataSource);
    }
}

