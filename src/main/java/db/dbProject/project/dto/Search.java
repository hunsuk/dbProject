package db.dbProject.project.dto;


import lombok.Data;

@Data
public class Search {

//  컬럼 필터
    private String name;
    private String ssn;
    private String Bdate;
    private String Address;
    private String Sex;
    private String Salary;
    private String Supervisor;
    private String Department;

    public Search(){

    }

    public Search(String name,String ssn, String Bdate,String Address,String Sex, String Salary, String Supervisor, String Department){
        this.name = name;
        this.ssn = ssn;
        this.Bdate = Bdate;
        this.Address = Address;
        this.Sex = Sex;
        this.Salary = Salary;
        this.Supervisor = Supervisor;
        this.Department =Department;
    }

}
