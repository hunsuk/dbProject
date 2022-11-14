package db.dbProject.project.dto;

import lombok.Data;

@Data
public class DnoSalary {
    private String arithmetic;
    private String salary;
    private String dno;

    public DnoSalary(String dno, String arithmetic, String salary) {
        this.arithmetic = arithmetic;
        this.salary = salary;
        this.dno = dno;
    }
}
