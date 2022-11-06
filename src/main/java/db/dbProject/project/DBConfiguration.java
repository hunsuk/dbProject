package db.dbProject.project;


import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DBConfiguration {

    //mySql 서버 접속
    @Bean
    public DataSource dataSource(){
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url(ConnectionConst.url)
                .username(ConnectionConst.user)
                .password(ConnectionConst.password)
                .build();
    }
}
