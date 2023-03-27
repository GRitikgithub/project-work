package com.workingsession.controller;

import com.workingsession.repository.EmployeeRepository;
import com.workingsession.repository.EmployeeTechnologyRepository;
import com.workingsession.request.EmployeeRequest;
import com.workingsession.response.EmployeeResponse;
import com.workingsession.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeRepository employeeRepository;


    @DeleteMapping("/employee/{employeeId}")
    public void delete(@PathVariable Integer employeeId){
        this.employeeService.deleteEmployee(employeeId);
    }
    @PostMapping("/employees")
    public String addData(@RequestBody EmployeeRequest employeeRequest){
        this.employeeService.insert(employeeRequest);
        return "New employee added";
    }
}
