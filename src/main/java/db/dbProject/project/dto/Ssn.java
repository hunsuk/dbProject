package db.dbProject.project.dto;

import lombok.Data;

@Data
public class Ssn {
    private String ssn;

    public Ssn(String ssn) {
        this.ssn = ssn;

    }
}
