package com.example.employeePortal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeePortal.model.Employee;
import com.example.employeePortal.repository.EmployeeRepository;



@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository empRepo;
	
	public Employee addEmployee(Employee emp) {
	    return empRepo.save(emp);
	}

	public List<Employee> getAllEmployees() {
	    return empRepo.findAll();
	}

	public Employee updateEmployee(Long empId, Employee emp) {
       
        Optional<Employee> employee = empRepo.findById(empId);
        if(employee.isPresent()){
        	Employee empNew = employee.get();
        	empNew.setFirstName(emp.getFirstName());
        	empNew.setLastName(emp.getLastName());
        	empNew.setEmailId(emp.getEmailId());
            return empRepo.save(empNew);   
        }
        else
        {
        	return null;
        }
}
	
	public void deleteEmployee(Long empId) {
		empRepo.deleteById(empId);
	}

	public Optional<Employee> findById(Long employeeId) {
		 Optional<Employee> employee = empRepo.findById(employeeId);
		 if(employee.isPresent()){
			 return employee;
		 }
		 else
		 {
			 return null;
		 }

	}
}
