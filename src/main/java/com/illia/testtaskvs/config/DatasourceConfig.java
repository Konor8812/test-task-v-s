package com.illia.testtaskvs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Configuration
public class DatasourceConfig {

    @Bean
    public DataSource getDataSource(@Value("${spring.datasource.driver-class-name}") String driveClassName,
                                    @Value("${spring.datasource.url}") String url) throws IOException {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driveClassName);
        dataSourceBuilder.url(url);

        var env = System.getenv();
        var datasourceUsername = env.get("DATASOURCE_USERNAME");
        var datasourcePassword= env.get("DATASOURCE_PASSWORD");
        if(datasourceUsername == null && datasourcePassword == null){
            List<String> datasourceCredentials = readCreds();
            datasourceUsername = datasourceCredentials.get(0);
            datasourcePassword = datasourceCredentials.get(1);
        }
        dataSourceBuilder.username(datasourceUsername);
        dataSourceBuilder.password(datasourcePassword);
        return dataSourceBuilder.build();
    }

    // for local runs
    private List<String> readCreds() throws IOException {
        return Files.readAllLines(Paths.get("datasource-credentials.txt"));
    }

}
