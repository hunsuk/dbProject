package db.dbProject.project.dto;

import lombok.Data;

@Data
public class Insert {
    private String fname;
    private String minit;
    private String lname;
    private String ssn;
    private String bdate;
    private String address;
    private String sex;
    private String salary;
    private String super_ssn;
    private String dno;

    public Insert(String fname, String minit, String lname, String ssn, String bdate, String address, String sex,String salary, String super_ssn, String dno){
        this.fname = fname;
        this.minit = minit;
        this.lname = lname;
        this.ssn = ssn;
        this.bdate = bdate;
        this.address = address;
        this.sex = sex;
        this.salary = salary;
        this.super_ssn = super_ssn;
        this.dno =dno;
    }

}
