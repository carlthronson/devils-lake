package personal.carlthronson.dl.be.test;

import java.security.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
public class TestController {
    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public String test(
            @RequestParam("limit") Optional<Integer> limit, Principal principal) {

        String user = "postgres";
        String password = "postgres";
        String host = "devils-lake.cvrbdk7xgglz.us-west-1.rds.amazonaws.com";
        String port = "5432";
        Object database = "devils_lake";
        String format = "jdbc:postgresql://%s:%s/%s";
        String url = String.format(format, host, port, database);

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            String sql = "select * from test";
            ResultSet rs = stmt.executeQuery(sql );
            while (rs.next()) {
                Integer id = rs.getInt("id");
                return "The first id is: " + id;
            }
        } catch (SQLException e) {
            return e.toString();
        }
        return "success";
    }
}
