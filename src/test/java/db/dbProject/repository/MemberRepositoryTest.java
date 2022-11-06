package db.dbProject.repository;

import db.dbProject.project.domain.Member;
import db.dbProject.project.repository.MemberRepository;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class MemberRepositoryTest {

        MemberRepository repository = new MemberRepository();


    @Test
    void crud() throws SQLException {
        Member member = new Member("member3",10000);
        repository.save(member);
    }
}