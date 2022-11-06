package db.dbProject.project.controller;


import db.dbProject.project.domain.EMPLOYEE;
import db.dbProject.project.domain.Search;
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

    @RequestMapping(value = "/api1", method = RequestMethod.GET)
    public String api1(){
        return "api1";
    }

    @PostMapping(value = "/api1")
    public String api2(@ModelAttribute Search search, Model model) throws SQLException {

        List<EMPLOYEE> employees = new ArrayList<EMPLOYEE>();
        EMPLOYEERepository EMPLOYEErepo = new EMPLOYEERepository();
        employees = EMPLOYEErepo.findByAll();
        model.addAttribute("events" ,employees);
        model.addAttribute("search" ,search);
        return "api2";
    }

}
