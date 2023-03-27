package com.workingsession.service;

import com.workingsession.exception.DataAlreadyExist;
import com.workingsession.exception.DataNotFoundException;
import com.workingsession.model.Employee;
import com.workingsession.model.EmployeeTechnology;
import com.workingsession.model.Technology;
import com.workingsession.repository.EmployeeRepository;
import com.workingsession.repository.EmployeeTechnologyRepository;
import com.workingsession.repository.TechnologyRepository;
import com.workingsession.request.EmployeeTechnologyRequest;
import com.workingsession.response.EmployeeResponse;
import com.workingsession.response.TechnologyResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.tool.schema.spi.ExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@Slf4j
public class EmployeeTechnologyService {
    @Autowired
    EmployeeTechnologyRepository employeeTechnologyRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    TechnologyRepository technologyRepository;


    public List<TechnologyResponse> getTechnology(Integer employeeId) {
        List<TechnologyResponse> technologyResponseList=new ArrayList<>();
        Optional<Employee> employeeOptional=employeeRepository.findById(employeeId);
        if(employeeOptional.isPresent()){
            if(!CollectionUtils.isEmpty(employeeOptional.get().getTechnologies())){
                for(Technology technology:employeeOptional.get().getTechnologies()){
                    TechnologyResponse technologyResponse=new TechnologyResponse();
                    technologyResponse.setId(technology.getId());
                    technologyResponse.setName(technology.getName());
                    technologyResponseList.add(technologyResponse);
                }
                log.info("Return list of all technology the employee knows.");
                return technologyResponseList;
            }else {
                return null;
            }

        }else{
            log.info("Data is not found");
            throw new DataNotFoundException("Data is not found");
        }
    }

    public List<EmployeeResponse> getEmployee(String technologyName) {
        List<EmployeeResponse> employeeResponseList=new ArrayList<>();
        Optional<Technology> technologyOptional=technologyRepository.findByName(technologyName);
        if(technologyOptional.isPresent()){
            if(CollectionUtils.isEmpty(technologyOptional.get().getEmployees())){
                return Collections.emptyList();
            }else{
                for(Employee employee:technologyOptional.get().getEmployees()){
                    EmployeeResponse employeeResponse=new EmployeeResponse();
                    employeeResponse.setId(employee.getId());
                    employeeResponse.setFirstName(employee.getFirstName());
                    employeeResponse.setLastName(employee.getLastName());
                    employeeResponse.setEmail(employee.getEmail());
                    employeeResponse.setGender(employee.getGender());
                    employeeResponseList.add(employeeResponse);
                }
                log.info("Data found");
                return employeeResponseList;
            }
        }else{
            log.info("Data is not found");
            throw new DataNotFoundException("Data is not found");
        }
    }

    public ResponseEntity<EmployeeTechnology> insert(Integer employeeId, Integer technologyId,
                                                     EmployeeTechnologyRequest employeeTechnologyRequest) {
        Optional<Employee> employeeOptional= employeeRepository.findById(employeeId);
        Optional<Technology> technologyOptional=technologyRepository.findById(technologyId);
        if (employeeOptional.isPresent()&&technologyOptional.isPresent()){
            Optional<EmployeeTechnology> employeeTechnologyOptional=employeeTechnologyRepository.findByEmployeeIdAndTechnologyId(employeeId,technologyId);
            if(employeeTechnologyOptional.isPresent()){
                throw new DataAlreadyExist("Employee is already enroll with technology");
            }else{
                if(employeeTechnologyRequest.getExpertiseLevel().equalsIgnoreCase("Beginner")||
                        employeeTechnologyRequest.getExpertiseLevel().equalsIgnoreCase("Mediator")||
                        employeeTechnologyRequest.getExpertiseLevel().equalsIgnoreCase("Expertise")){
                    EmployeeTechnology employeeTechnology=new EmployeeTechnology();
                    employeeTechnology.setEmployeeId(employeeId);
                    employeeTechnology.setTechnologyId(technologyId);
                    employeeTechnology.setExpertiseLevel(employeeTechnologyRequest.getExpertiseLevel());
                    return new ResponseEntity<>(employeeTechnologyRepository.save(employeeTechnology), HttpStatus.CREATED);
                }else{
                    throw new DataAlreadyExist("Expertise level is different from expected");
                }
            }

        }else{
            throw new DataNotFoundException("Data not found with employee id and technology id");
        }

    }

}
