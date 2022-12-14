package db.dbProject.project.repository;


import db.dbProject.project.DBConnectoinUtil;
import db.dbProject.project.domain.Dependent;
import db.dbProject.project.domain.EMPLOYEE;
import db.dbProject.project.dto.DnoSalary;
import db.dbProject.project.dto.Insert;
import db.dbProject.project.dto.Search;
import db.dbProject.project.dto.Update;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Slf4j
public class EMPLOYEERepository {

    Calendar cal = new GregorianCalendar();
    Timestamp ts = new Timestamp(cal.getTimeInMillis()); //


    public ArrayList<Dependent> search_dependent(String ssn_list) throws SQLException {

        String [] ssn = ssn_list.split(" ");
        ArrayList<Dependent> dependent_list =  new ArrayList<Dependent>();
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        for(int i = 0; i < ssn.length; i++){
            String sql = "select * from DEPENDENT,EMPLOYEE where Ssn = Essn AND Essn = ?";
            try{
                con = getConnection();
                pstm=con.prepareStatement(sql);
                pstm.setString(1,ssn[i]);
                rs =pstm.executeQuery();
                while (rs.next()){
                    Dependent dependent = new Dependent();
                    dependent.setName(rs.getString("Fname") +" "+ rs.getString("Minit") +" " +rs.getString("Lname"));
                    dependent.setDependent_name(rs.getString("Dependent_name"));
                    dependent.setSex(rs.getString("Sex"));
                    dependent.setBdate(rs.getDate("Bdate").toString());
                    dependent.setRelationship(rs.getString("Relationship"));
                    dependent_list.add(dependent);
                }
            }catch(SQLException e) {
                log.error("db error",e);
                throw e;
            } finally {
                close(con, pstm, null);
            }

        }
        return  dependent_list;
    }
    public void update(Update update) throws SQLException {
        String sql = "update EMPLOYEE set "+update.getUpdate()+"=?, modified=? where Ssn=?";

        Connection con = null;
        PreparedStatement pstm = null;

        String [] ssn = update.getSsn().split(" ");


        for(int i = 0; i < ssn.length; i++) {
            try {
                con = getConnection();
                pstm = con.prepareStatement(sql);
                pstm.setString(1, update.getUpdate_search());
                pstm.setTimestamp(2, ts);
                pstm.setString(3, ssn[i]);
                pstm.executeUpdate();

            } catch (SQLException e) {
                log.error("db error", e);
                throw e;
            } finally {
                close(con, pstm, null);
            }
        }
    }

    public void delete(String[] update) throws SQLException {
        String sql = "delete from EMPLOYEE where Ssn=?";
        Connection con = null;
        PreparedStatement pstm = null;

        for(int i = 0; i < update.length; i++) {
            try {
                con = getConnection();
                pstm = con.prepareStatement(sql);
                pstm.setString(1,update[i]);
                pstm.executeUpdate();
            } catch (SQLException e) {
                log.error("db error", e);
                throw e;
            } finally {
                close(con, pstm, null);
            }
        }
    }

    public Insert save(Insert employee) throws SQLException {
        String sql = "insert into EMPLOYEE(Fname, Minit, Lname, Ssn, Bdate, Address, Sex, Salary, Super_ssn, Dno, Created, Modified)" +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, employee.getFname());
            pstm.setString(2, employee.getMinit());
            pstm.setString(3, employee.getLname());
            pstm.setString(4, employee.getSsn());
            pstm.setDate(5, java.sql.Date.valueOf((employee.getBdate())));
            pstm.setString(6, employee.getAddress());
            pstm.setString(7, employee.getSex());
            pstm.setString(8, employee.getSalary());
            pstm.setString(9, employee.getSuper_ssn());
            pstm.setInt(10,  Integer.valueOf(employee.getDno()));
            pstm.setTimestamp(11, ts);
            pstm.setTimestamp(12, ts);

            pstm.executeUpdate();
            return employee;
        } catch (SQLException e) {
            log.error("?????? ??????", e);
            throw e;
        } finally {
            close(con, pstm, null);
        }
    }
    public List<EMPLOYEE> findBySQL(String range, String value) throws SQLException{
        String target_column_sql = null;
        String condition = "=" + "\"" + value + "\"";


        if (range.equals("Dname")){
            target_column_sql = "Dname" +condition;
        } else if(range.equals("sex")){
            target_column_sql ="e1.sex" +condition;
        } else if(range.equals("salary")){
            condition = ">" + "\"" + value + "\"";
            target_column_sql ="e1.salary" +condition;
        } else if(range.equals("bdate")){
            condition =" LIKE '_____"+value+"___'";
            target_column_sql = "e1.bdate" + condition;
        } else{
            String [] name = value.split(" ");
            target_column_sql = "e2.Fname = " + "\""+name[0]+ "\"" +" AND "+ "e2.Minit = " + "\"" + name[1] + "\"" +" AND " + "e2.Lname = " + "\"" + name[2] + "\"";
        }



        String sql = "select e1.Fname, e1.Minit ,e1.Lname, e1.Ssn, e1.Bdate, e1.Address, e1.Sex, e1.Salary, e2.Fname Super_Fname,e2.Minit Super_Minit, e2.Lname Super_Lname, d.Dname " +
                    "from EMPLOYEE e1 LEFT OUTER JOIN EMPLOYEE e2 ON e1.Super_ssn = e2.Ssn, DEPARTMENT d where e1.Dno = d.Dnumber AND " + target_column_sql;
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
                employee.setName(rs.getString("Fname") + " "+rs.getString("Minit") + " " + rs.getString("Lname"));
                employee.setSsn(rs.getString("Ssn"));
                employee.setBdate(rs.getDate("Bdate"));
                employee.setAddress(rs.getString("Address"));
                employee.setSex(rs.getString("Sex"));
                employee.setSalary(rs.getDouble("Salary"));
                if(rs.getString("Super_Fname") == null){
                    employee.setSuper_Name("");
                }else{
                    employee.setSuper_Name(rs.getString("Super_Fname") + " " + rs.getString("Super_Minit") + " "+ rs.getString("Super_Lname"));
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
    public List<EMPLOYEE> findByAll(Search search) throws SQLException{
//        String search_sql = "select ";
//
//        if(search.getName().equals("name")){
//            search_sql += "e1.Fname, e1.Minit ,e1.Lname";
//        }
//
//        if(search.getSsn().equals("ssn")){
//            search_sql += " ,e1.Ssn";
//        }
//
//        if(search.getBdate().equals("Bdate")){
//            search_sql += " ,e1.Bdate";
//        }
//
//        if(search.getAddress().equals("Address")){
//            search_sql +=  " ,e1.Address";
//        }
//
//        if(search.getSex().equals("Sex")){
//            search_sql += " ,e1.Sex";
//        }
//
//        if(search.getSalary().equals("Salary")){
//            search_sql += " ,e1.Salary";
//        }
//
//        if(search.getSupervisor().equals("Supervisor")){
//            search_sql += " ,e2.Fname Super_Fname ,e2.Minit Super_Minit ,e2.Lname Super_Lname";
//        }
//
//        if(search.getDepartment().equals("Department")){
//            search_sql += " ,d.Dname ";
//        }


        String sql = "select e1.Fname, e1.Minit ,e1.Lname ,e1.Ssn ,e1.Bdate ,e1.Address ,e1.Sex ,e1.Salary ,e2.Fname Super_Fname ,e2.Minit Super_Minit ,e2.Lname Super_Lname ,d.Dname from EMPLOYEE e1 LEFT OUTER JOIN EMPLOYEE e2 ON e1.Super_ssn = e2.Ssn, DEPARTMENT d where e1.Dno = d.Dnumber";
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
                employee.setName(rs.getString("Fname") + " "+rs.getString("Minit") + " " + rs.getString("Lname"));
                employee.setSsn(rs.getString("Ssn"));
                employee.setBdate(rs.getDate("Bdate"));
                employee.setAddress(rs.getString("Address"));
                employee.setSex(rs.getString("Sex"));
                employee.setSalary(rs.getDouble("Salary"));
                if(rs.getString("Super_Fname") == null){
                    employee.setSuper_Name("");
                }else{
                    employee.setSuper_Name(rs.getString("Super_Fname") + " " + rs.getString("Super_Minit") + " "+ rs.getString("Super_Lname"));
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

    public void updateAllDnoSalary(DnoSalary update) throws SQLException {

        String sql = "update EMPLOYEE set salary = salary "+update.getArithmetic()+" ?, modified=? where Dno=?";

        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, update.getSalary());
            pstm.setTimestamp(2, ts);
            pstm.setString(3, update.getDno());
            pstm.executeUpdate();

        } catch (SQLException e) {
            log.error("db error", e);
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
