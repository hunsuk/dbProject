package db.dbProject.project.domain;


import lombok.Data;
import java.sql.Date;

@Data
public class InputEmployee {
    private String Fname;
    private String Minit;
    private String Lname;
    private String Ssn;
    private java.sql.Date Bdate;
    private String Address;
    private String Sex;
    private Double Salary;
    private String Super_ssn;
    private int Dno;

    public InputEmployee() {
    }

    public InputEmployee(String fname, String minit, String lname, String ssn, Date bdate, String address,
                    String sex, Double salary, String super_ssn, int dno) {
        Fname = fname;
        Minit = minit;
        Lname = lname;
        Ssn = ssn;
        Bdate = bdate;
        Address = address;
        Sex = sex;
        Salary = salary;
        Super_ssn = super_ssn;
        Dno = dno;
    }
}
