package db.dbProject.project;


import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class DBConnectoinUtil {

    public static Connection getConnection(){
        try {
            Connection connection = DriverManager.getConnection(ConnectionConst.url, ConnectionConst.user, ConnectionConst.password);
            log.info("정상적으로 연결되었습니다. 클래스정보 : {}",connection.getClass());
            return connection;
        } catch (SQLException e) {
            log.error("연결할 수 없습니다");
            throw new IllegalStateException(e);
        }
    }
}
