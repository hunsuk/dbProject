package db.dbProject.project.controller;


import db.dbProject.project.domain.EMPLOYEE;
import db.dbProject.project.domain.InputEmployee;
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

    @PostMapping(value = "/api1/add")
    public String api3(@ModelAttribute InputEmployee employee) throws SQLException {

        EMPLOYEERepository EMPLOYEErepo = new EMPLOYEERepository();

        InputEmployee newEmployee = new InputEmployee(employee.getFname(), employee.getMinit(), employee.getLname(),
                employee.getSsn(), employee.getBdate(), employee.getAddress(), employee.getSex(),
                employee.getSalary(), employee.getSuper_ssn(), employee.getDno());

        EMPLOYEErepo.save(newEmployee);

        return "api1";
    }
}
