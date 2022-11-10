package db.dbProject.project.controller;

import db.dbProject.project.domain.*;
import db.dbProject.project.repository.EMPLOYEERepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class GetApi {

    EMPLOYEERepository EMPLOYEErepo = new EMPLOYEERepository();

    @RequestMapping(value = "/api1", method = RequestMethod.GET)
    public String api1(){
        return "api1";
    }

    @PostMapping(value = "/api2")
    public String api2(@ModelAttribute Search search, @ModelAttribute Search_sub search_sub, Model model) throws SQLException {
        log.info(search_sub.getRange());
        List<EMPLOYEE> employees = new ArrayList<EMPLOYEE>();
        EMPLOYEERepository EMPLOYEErepo = new EMPLOYEERepository();

        log.info(search_sub.getInput_search());
        if (search_sub.getRange().equals("default")){
            employees = EMPLOYEErepo.findByAll();
        }else{
            employees = EMPLOYEErepo.findBySQL(search_sub.getRange(),search_sub.getInput_search());
        }

        model.addAttribute("events" ,employees);
        model.addAttribute("events_size",employees.size());
        model.addAttribute("search" ,search);
        return "api2";
    }

    @PostMapping("/api2/employee")
    public String api3(@ModelAttribute InputEmployee inputEmployee) throws SQLException {
        EMPLOYEErepo.save(inputEmployee);

        return "api1";
    }
    @PutMapping( "/api2/employee")
    public String api4(@ModelAttribute UpdateEmployee updateEmployee) throws SQLException {
        EMPLOYEErepo.update(updateEmployee);

        return "api1";
    }
    @DeleteMapping("/api2/employee")
    public String api5(@ModelAttribute DeleteEmployee deleteEmployee) throws SQLException {
        EMPLOYEErepo.delete(deleteEmployee);

        return "api1";
    }

//    //유저 마이페이지
//    @GetMapping(value = "/myPage")
//    public String api3()

}
