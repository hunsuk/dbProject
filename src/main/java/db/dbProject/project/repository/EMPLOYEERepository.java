package db.dbProject.project.repository;


import db.dbProject.project.DBConnectoinUtil;
import db.dbProject.project.domain.EMPLOYEE;
import db.dbProject.project.domain.InputEmployee;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Slf4j
public class EMPLOYEERepository {


    public List<EMPLOYEE> findBySQL(String range, String value) throws SQLException{
        String target_column = null;
        if (range.equals("department")){
            target_column = "Dname";
        } else if(range.equals("sex")){
            target_column ="Sex";
        }


        String sql = "select e1.Fname, e1.Lname, e1.Ssn, e1.Bdate, e1.Address, e1.Sex, e1.Salary, e2.Fname Super_Fname, e2.Lname Super_Lname, d.Dname " +
                "from EMPLOYEE e1 LEFT OUTER JOIN EMPLOYEE e2 ON e1.Super_ssn = e2.Ssn, DEPARTMENT d where e1.Dno = d.Dnumber AND "+target_column +"=" + "\"" + value + "\"";
        log.info(sql);

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<EMPLOYEE> data_rs = new ArrayList<EMPLOYEE>();

        try{
            con = getConnection();
            pstm=con.prepareStatement(sql);
            rs =pstm.executeQuery();

            while (rs.next()){
                EMPLOYEE employee = new EMPLOYEE();
                employee.setName(rs.getString("Fname") + " " + rs.getString("Lname"));
                employee.setSsn(rs.getString("Ssn"));
                employee.setBdate(rs.getDate("Bdate"));
                employee.setAddress(rs.getString("Address"));
                employee.setSex(rs.getString("Sex"));
                employee.setSalary(rs.getDouble("Salary"));
                if(rs.getString("Super_Fname") == null){
                    employee.setSuper_Name("");
                }else{
                    employee.setSuper_Name(rs.getString("Super_Fname") + " " + rs.getString("Super_Lname"));
                }
                employee.setDno(rs.getString("Dname"));
                log.info(employee.toString());
                data_rs.add(employee);
            }
            return data_rs;
        }catch(SQLException e) {
            log.error("db error",e);
            throw e;
        } finally {
            close(con, pstm, null);
        }
    }

    public List<EMPLOYEE> findByAll() throws SQLException{

        String sql = "select e1.Fname, e1.Lname, e1.Ssn, e1.Bdate, e1.Address, e1.Sex, e1.Salary, e2.Fname Super_Fname, e2.Lname Super_Lname, d.Dname " +
                "from EMPLOYEE e1 LEFT OUTER JOIN EMPLOYEE e2 ON e1.Super_ssn = e2.Ssn, DEPARTMENT d where e1.Dno = d.Dnumber";
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<EMPLOYEE> data_rs = new ArrayList<EMPLOYEE>();
        try{
            con = getConnection();
            pstm=con.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()){
                EMPLOYEE employee = new EMPLOYEE();
                employee.setName(rs.getString("Fname") + " " + rs.getString("Lname"));
                employee.setSsn(rs.getString("Ssn"));
                employee.setBdate(rs.getDate("Bdate"));
                employee.setAddress(rs.getString("Address"));
                employee.setSex(rs.getString("Sex"));
                employee.setSalary(rs.getDouble("Salary"));
                if(rs.getString("Super_Fname") == null){
                    employee.setSuper_Name("");
                }else{
                    employee.setSuper_Name(rs.getString("Super_Fname") + " " + rs.getString("Super_Lname"));
                }
                employee.setDno(rs.getString("Dname"));
                log.info(employee.toString());
                data_rs.add(employee);
            }
            return data_rs;
        }catch(SQLException e) {
            log.error("db error",e);
            throw e;
        } finally {
            close(con,pstm,null);
        }
    }

    public InputEmployee save(InputEmployee employee) throws SQLException {
        String sql = "insert into employee(Fname, Minit, Lname, Ssn, Bdate, Address, Sex, Salary, Super_ssn, Dno, Created, Modified)" +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement pstm = null;

        try {
            Calendar cal = new GregorianCalendar();
            Timestamp ts = new Timestamp(cal.getTimeInMillis());

            con = getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, employee.getFname());
            pstm.setString(2, employee.getMinit());
            pstm.setString(3, employee.getLname());
            pstm.setString(4, employee.getSsn());
            pstm.setDate(5, employee.getBdate());
            pstm.setString(6, employee.getAddress());
            pstm.setString(7, employee.getSex());
            pstm.setDouble(8, employee.getSalary());
            pstm.setString(9, employee.getSuper_ssn());
            pstm.setInt(10, employee.getDno());
            pstm.setTimestamp(11, ts);
            pstm.setTimestamp(12, ts);

            pstm.executeUpdate();
            return employee;
        } catch (SQLException e) {
            log.error("삽입 오류", e);
            throw e;
        } finally {
            close(con, pstm, null);
        }

    }

    private static Connection getConnection() {
        return DBConnectoinUtil.getConnection();
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
}
