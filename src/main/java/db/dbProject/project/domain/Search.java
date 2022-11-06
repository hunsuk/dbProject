package db.dbProject.project.domain;


import lombok.Data;

@Data
public class Search {
    private String range;
    private String input_search;
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

    public Search(String range,String input_search, String name,String ssn, String Bdate,String Address,String Sex, String Salary, String Supervisor, String Department){
        this.range = range;
        this.input_search = input_search;
        this.ssn = ssn;
        this.Bdate = Bdate;
        this.Address = Address;
        this.Sex = Sex;
        this.Salary = Salary;
        this.Supervisor = Supervisor;
        this.Department =Department;
    }

}
