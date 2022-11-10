package db.dbProject.project.domain;

import lombok.Data;

@Data
public class DeleteEmployee {
    String Fname;
    String Ssn;

    public DeleteEmployee() {
    }

    public DeleteEmployee(String fname, String ssn) {
        Fname = fname;
        Ssn = ssn;
    }
}
