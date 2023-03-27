package com.workingsession.repository;

import com.workingsession.model.EmployeeTechnology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeTechnologyRepository extends JpaRepository<EmployeeTechnology,Integer> {

    Optional<EmployeeTechnology> findByEmployeeIdAndTechnologyId(Integer employeeId, Integer technologyId);

   // List<EmployeeTechnology> findByEmployeeId(Integer employeeId);

    List<EmployeeTechnology> findByemployeeId(Integer employeeId);
}
