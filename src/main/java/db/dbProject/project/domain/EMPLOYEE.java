package db.dbProject.project.domain;


import lombok.Data;

import java.sql.Date;

@Data
public class EMPLOYEE {


    private String Fname;
    private String Lname;
    private String Ssn;
    private Date Bdate;
    private String Address;
    private String Sex;
    private Double Salary;
    private String Super_ssn;
    private Integer Dno;


    public EMPLOYEE(){

    }
    public EMPLOYEE(String Fname, String Lname, String Ssn, Date Bdate, String Address, String Sex, Double Salary, String Super_ssn, Integer Dno){
        this.Fname  = Fname;
        this.Lname = Lname;
        this.Ssn = Ssn;
        this.Bdate = Bdate;
        this.Address = Address;
        this.Sex = Sex;
        this.Salary = Salary;
        this.Super_ssn = Super_ssn;
        this.Dno = Dno;
    }

}
