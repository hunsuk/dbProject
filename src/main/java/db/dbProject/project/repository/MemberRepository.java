package db.dbProject.project.repository;


import db.dbProject.project.DBConnectoinUtil;
import db.dbProject.project.domain.Member;
import lombok.extern.slf4j.Slf4j;
import java.sql.*;

/**
 * jdbc 사용
 */

@Slf4j
public class MemberRepository {
    public Member save(Member member) throws SQLException{
        String sql = "insert into member(member_id, money) values(?,?)";

        Connection con = null;
        PreparedStatement pstm = null;

        try{
            con = getConnection();
            pstm=con.prepareStatement(sql);

            pstm.setString(1,member.getMemberId());
            pstm.setInt(2,member.getMoney());

            pstm.executeUpdate();
            return member;
        }catch(SQLException e) {
            log.error("db error",e);
            throw e;
        } finally {
            close(con,pstm,null);
        }
    }

    private void close(Connection con, Statement stmt, ResultSet rs){

        if(rs != null){
            try{
                rs.close();
            }catch (SQLException e){
                log.info("error",e);
            }
        }

        if(stmt != null){
            try{
                stmt.close();
            }catch (SQLException e){
                log.info("error",e);
            }
        }
        if(con != null){
            try{
                con.close();
            }catch (SQLException e){
                log.info("error",e);
            }
        }
    }
    private static Connection getConnection() {
        return DBConnectoinUtil.getConnection();
    }
}
