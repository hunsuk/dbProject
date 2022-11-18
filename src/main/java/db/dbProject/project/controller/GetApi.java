package db.dbProject.project.controller;


import db.dbProject.project.domain.Dependent;
import db.dbProject.project.domain.EMPLOYEE;
import db.dbProject.project.dto.*;
import db.dbProject.project.repository.EMPLOYEERepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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

    @PostMapping(value = "/api2")
    public String api2(@ModelAttribute Search search, @ModelAttribute Search_sub search_sub, Model model) throws SQLException {
        log.info(search_sub.getRange());
        List<EMPLOYEE> employees = new ArrayList<EMPLOYEE>();
        EMPLOYEERepository EMPLOYEErepo = new EMPLOYEERepository();

        log.info(search_sub.getInput_search());
        if (search_sub.getRange().equals("default")){
            employees = EMPLOYEErepo.findByAll(search);
        }else{
            employees = EMPLOYEErepo.findBySQL(search_sub.getRange(),search_sub.getInput_search());
        }

        model.addAttribute("events" ,employees);
        model.addAttribute("events_size",employees.size());
        model.addAttribute("search" ,search);
        return "api2";
    }
    @PostMapping(value = "/api3")
    public String api3(@ModelAttribute Insert insert, Model model) throws SQLException {
        EMPLOYEERepository EMPLOYEErepo = new EMPLOYEERepository();
//        model.addAttribute("search" ,search);
        EMPLOYEErepo.save(insert);
        return "redirect:/api1";
    }

    @PostMapping(value = "/api4")
    public String api4(@ModelAttribute Update update, Model model) throws SQLException {
        EMPLOYEERepository EMPLOYEErepo = new EMPLOYEERepository();

        EMPLOYEErepo.delete(update.getSsn().split(" "));

        return "redirect:/api1";
    }
    @PostMapping(value = "/api5")
    public String api5(@ModelAttribute Update update, Model model) throws SQLException {
        EMPLOYEERepository EMPLOYEErepo = new EMPLOYEERepository();

        EMPLOYEErepo.update(update);
        return "redirect:/api1";
    }

    @PostMapping(value = "/api6")
    public String api6(@ModelAttribute DnoSalary update, Model model) throws SQLException {
        EMPLOYEERepository EMPLOYEErepo = new EMPLOYEERepository();

        EMPLOYEErepo.updateAllDnoSalary(update);
        return "redirect:/api1";
    }
    @PostMapping(value = "/api7")
    public String api7(@ModelAttribute Ssn ssn, Model model) throws SQLException {
        EMPLOYEERepository EMPLOYEErepo = new EMPLOYEERepository();
        List<EMPLOYEE> employees = new ArrayList<EMPLOYEE>();
        ArrayList<Dependent> dependents = new ArrayList<Dependent>();

        log.info("ssn_list: :" + ssn.getSsn());

        Search search = new Search("name","ssn","Bdate","Address","Sex","Salary","Supervisor","Department");
        employees = EMPLOYEErepo.findByAll(search);
        dependents = EMPLOYEErepo.search_dependent(ssn.getSsn());



        model.addAttribute("events" ,employees);
        model.addAttribute("events_size",employees.size());
        model.addAttribute("search" ,search);
        model.addAttribute("dependents",dependents);

        return "/api2";
    }

//    //유저 마이페이지
//    @GetMapping(value = "/myPage")
//    public String api3()

}
