package db.dbProject.project.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Dependent {
    private String Name;
    private String Dependent_name;
    private String Sex;
    private String Bdate;
    private String Relationship;

    public Dependent(String Name, String Dependent_name, String Sex, String Bdate, String Relationship){
        this.Name =Name;
        this.Dependent_name = Dependent_name;
        this.Sex = Sex;
        this.Bdate = Bdate;
        this.Relationship =Relationship;
    }

}
