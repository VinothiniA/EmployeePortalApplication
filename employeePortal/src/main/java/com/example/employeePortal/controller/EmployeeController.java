package com.example.employeePortal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeePortal.model.Employee;
import com.example.employeePortal.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService empService;
	
	//to retrieve list of employees
	@RequestMapping(value="/getEmployee",method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllEmployees() {
		try{
			List<Employee> emp = new ArrayList<Employee>();
			emp = empService.getAllEmployees();
	 		return new ResponseEntity<>(emp, HttpStatus.OK);
	       } 
		catch (Exception e) {
		   return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
	
	@RequestMapping(value="/getEmployee/{id}",method = RequestMethod.GET)
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable(value = "id") Long employeeId)
     {
        Optional<Employee> employee = empService.findById(employeeId);
        return new ResponseEntity<>(employee,HttpStatus.OK);
          
    }
	
	//to add new employee
	@RequestMapping(value="/addEmployee",method = RequestMethod.POST)
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee emp) {
	    try{
		   Employee employee=  empService.addEmployee(emp);	    
	       return new ResponseEntity<>(employee, HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}

	//to update employee by id
	@RequestMapping(value="/editEmployee/{empId}",method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "empId") Long id, @RequestBody Employee empDetails) {
	  try{
	       Employee emp= empService.updateEmployee(id, empDetails);
	       if(emp!=null)
	       {
	    	   return new ResponseEntity<>(emp,HttpStatus.OK);
	       }
	       else
	       {
	    	   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	       }
	     }
	  catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
    //to delete an employee
	@RequestMapping(value="/deleteEmployee/{empId}",method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable(value = "empId") Long id) {
	    try{
		     empService.deleteEmployee(id);
	         return new ResponseEntity<>(HttpStatus.OK);
	      } catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}

}