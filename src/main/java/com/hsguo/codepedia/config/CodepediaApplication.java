package com.hsguo.codepedia.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@ComponentScan("com.hsguo")
@SpringBootApplication
public class CodepediaApplication {
    private static final Logger LOG = LoggerFactory.getLogger(CodepediaApplication.class);
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CodepediaApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("Successfully started!");
        // 读取配置项来得到端口号
        LOG.info("address: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }

}
