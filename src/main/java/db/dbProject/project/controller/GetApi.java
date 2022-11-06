package db.dbProject.project.controller;


import db.dbProject.project.domain.EMPLOYEE;
import db.dbProject.project.repository.EMPLOYEERepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GetApi {

    @RequestMapping(value = "/api1", method = RequestMethod.GET)
    public String api1(){
        return "api1";
    }

    @RequestMapping(value = "/api1", method = RequestMethod.POST)
    public String api2(Model model) throws SQLException {

        List<EMPLOYEE> employees = new ArrayList<EMPLOYEE>();

        EMPLOYEERepository EMPLOYEErepo = new EMPLOYEERepository();
        employees = EMPLOYEErepo.findByAll();
        model.addAttribute("events" ,employees);
        return "api2";
    }

}
