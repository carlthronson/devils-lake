package personal.carlthronson.dl.be;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
    
    @Bean
    public DataSource getDataSource() {
        String user = "postgres";
        String password = "postgres";
        String host = "devils-lake.cvrbdk7xgglz.us-west-1.rds.amazonaws.com";
        String port = "5432";
        Object database = "devils_lake";
        String format = "jdbc:postgresql://%s:%s/%s";
        String url = String.format(format, host, port, database);
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(user);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }
}