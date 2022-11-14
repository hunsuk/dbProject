package db.dbProject.project.dto;


import lombok.Data;

@Data
public class Update {

    private String update;
    private String update_search;
    private String ssn;

    public Update(String update, String update_search, String ssn){
        this.update = update;
        this.update_search = update_search;
        this.ssn = ssn;
    }
}
