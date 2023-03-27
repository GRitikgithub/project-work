package com.workingsession.controller;

import com.workingsession.repository.EmployeeTechnologyRepository;
import com.workingsession.request.EmployeeTechnologyRequest;
import com.workingsession.response.EmployeeResponse;
import com.workingsession.response.TechnologyResponse;
import com.workingsession.service.EmployeeTechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeTechnologyController {

    @Autowired
    EmployeeTechnologyService employeeTechnologyService;
    @Autowired
    EmployeeTechnologyRepository employeeTechnologyRepository;

    @GetMapping("/employees/{employeeId}/technology")
    public List<TechnologyResponse> get(@PathVariable Integer employeeId){
        return employeeTechnologyService.getTechnology(employeeId);
    }
    @GetMapping("/technology/{technologyName}/employee")
    public List<EmployeeResponse> getByName(@PathVariable String technologyName){
        return employeeTechnologyService.getEmployee(technologyName);
    }
        @PostMapping("employees/{employeeId}/technology/{technologyId}")
        public String post(@PathVariable Integer employeeId, @PathVariable Integer technologyId, @RequestBody EmployeeTechnologyRequest employeeTechnologyRequest){
            this.employeeTechnologyService.insert(employeeId,technologyId,employeeTechnologyRequest);
            return "Employee add to technology";
        }
}

