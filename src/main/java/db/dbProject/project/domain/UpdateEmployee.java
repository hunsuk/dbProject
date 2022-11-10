package db.dbProject.project.domain;

import lombok.Data;

@Data
public class UpdateEmployee {
    String condition;
    String newValue;
    String Fname;
    String Ssn;

    public UpdateEmployee() {
    }

    public UpdateEmployee(String condition, String newValue, String fname, String ssn) {
        this.condition = condition;
        this.newValue = newValue;
        Fname = fname;
        Ssn = ssn;
    }
}
