package com.techutube.employee.controller;

import com.techutube.employee.entity.Employee;
import com.techutube.employee.model.EmployeeModel;
import com.techutube.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping(path =  "/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable(value = "id") int id){
        return employeeService.getEmployee(id);
    }

    @PostMapping(path = "/")
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeModel employee){
        return employeeService.addNewEmployee(employee);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Employee> editEmployee(@PathVariable(value = "id") int id, @RequestBody EmployeeModel employee){
        return employeeService.editEmployee(id, employee);
    }

    @GetMapping(path =  "/")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int id) {
        return employeeService.deleteEmplyee(id);
    }
}
