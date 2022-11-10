package db.dbProject.project.domain;


import lombok.Data;

import java.sql.Date;

@Data
public class EMPLOYEE {

//  체크박스 체크 여부
    private String Name;
    private String Ssn;
    private Date Bdate;
    private String Address;
    private String Sex;
    private Double Salary;
    private String Super_Name;
    private String Dno;

// 검색 범위 여부
    private String range;

    public EMPLOYEE(){

    }
    public EMPLOYEE(String Name,  String Ssn, Date Bdate, String Address, String Sex, Double Salary, String Super_Name, String Dno){
        this.Name  = Name;
        this.Ssn = Ssn;
        this.Bdate = Bdate;
        this.Address = Address;
        this.Sex = Sex;
        this.Salary = Salary;
        this.Super_Name = Super_Name;
        this.Dno = Dno;
    }

}
