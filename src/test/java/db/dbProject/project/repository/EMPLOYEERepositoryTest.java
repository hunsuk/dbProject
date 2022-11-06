package db.dbProject.project.repository;

import db.dbProject.project.domain.EMPLOYEE;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class EMPLOYEERepositoryTest {

    @Test
    void findByAll() throws SQLException {
        List<EMPLOYEE> employees = new ArrayList<EMPLOYEE>();

        EMPLOYEERepository EMPLOYEErepo = new EMPLOYEERepository();
        employees = EMPLOYEErepo.findByAll();

        Assertions.assertThat(employees.size()).isEqualTo(8);
    }
}