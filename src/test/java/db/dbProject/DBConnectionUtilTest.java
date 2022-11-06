package db.dbProject;

import db.dbProject.project.DBConnectoinUtil;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

@Slf4j
class DBConnectionUtilTest {


    @Test
    void connection(){

        Connection connection = DBConnectoinUtil.getConnection();
        Assertions.assertThat(connection).isNotNull();
    }

}
