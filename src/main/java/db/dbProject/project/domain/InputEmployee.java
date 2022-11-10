package db.dbProject.project.domain;


import lombok.Data;
import java.sql.Date;
import java.sql.Timestamp;

@Data
public class InputEmployee {
    private String fname;
    private String minit;
    private String lname;
    private String ssn;
    private java.sql.Date bdate;
    private String address;
    private String sex;
    private String salary;
    private String super_ssn;
    private int dno;
    private Timestamp created;
    private Timestamp modified;

    public InputEmployee() {
    }

    public InputEmployee(String fname, String minit, String lname, String ssn, Date bdate, String address, String sex, String salary, String super_ssn, int dno) {
        this.fname = fname;
        this.minit = minit;
        this.lname = lname;
        this.ssn = ssn;
        this.bdate = bdate;
        this.address = address;
        this.sex = sex;
        this.salary = salary;
        this.super_ssn = super_ssn;
        this.dno = dno;
    }
}
