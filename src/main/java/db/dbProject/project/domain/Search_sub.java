package db.dbProject.project.domain;

import lombok.Data;

@Data
public class Search_sub {
//  검색 범위 필터
    private String range;
    private String input_search;


    public Search_sub(String range, String input_search){
        this.range = range;
        this.input_search = input_search;
    }
}
