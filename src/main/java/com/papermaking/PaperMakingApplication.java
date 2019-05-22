package com.papermaking;

import com.papermaking.common.util.SnowflakeIdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@MapperScan("com.papermaking.mapper")
public class PaperMakingApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaperMakingApplication.class, args);
    }

    @Bean
    public SnowflakeIdWorker snowflakeIdWorker() {
        return new SnowflakeIdWorker(1, 1);
    }
}
