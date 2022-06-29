package com.example.employeePortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employeePortal.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{

}
