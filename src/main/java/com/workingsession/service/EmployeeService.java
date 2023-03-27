package com.workingsession.service;

import com.workingsession.exception.DataAlreadyExist;
import com.workingsession.exception.DataNotFoundException;
import com.workingsession.model.Employee;
import com.workingsession.model.EmployeeTechnology;
import com.workingsession.repository.EmployeeRepository;
import com.workingsession.repository.EmployeeTechnologyRepository;
import com.workingsession.request.EmployeeRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeTechnologyRepository employeeTechnologyRepository;

     public void deleteEmployee(Integer employeeId) {
        Optional<Employee> employeeOptional=employeeRepository.findById(employeeId);
        if(employeeOptional.isPresent()){
            List<EmployeeTechnology> employeeTechnologyList=employeeTechnologyRepository.findByemployeeId(employeeId);
            if(employeeTechnologyList.isEmpty()){
                throw new DataNotFoundException("Data is not found");
            }else{
                employeeTechnologyRepository.deleteAll(employeeTechnologyList);
                employeeRepository.delete(employeeOptional.get());
                log.info("Data is delete successfully");
            }
        }else{
            throw new DataNotFoundException("Employee id doesn't exist");
        }
    }

   public ResponseEntity<Employee> insert(EmployeeRequest employeeRequest) {
        Employee duplicateEmail=employeeRepository.findByEmail(employeeRequest.getEmail());
        if(ObjectUtils.isEmpty(duplicateEmail)){
            Employee employee=new Employee();
            employee.setFirstName(employeeRequest.getFirstName());
            employee.setLastName(employeeRequest.getLastName());
            employee.setEmail(employeeRequest.getEmail());
            employee.setGender(employeeRequest.getGender());
            return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.CREATED);
        }else{
            throw new DataAlreadyExist("Email already exist");
        }


    }
}
