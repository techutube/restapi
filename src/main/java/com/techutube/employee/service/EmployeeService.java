package com.techutube.employee.service;

import com.techutube.employee.entity.Employee;
import com.techutube.employee.model.EmployeeModel;
import com.techutube.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public ResponseEntity<List<Employee>> getAllEmployee() {
        return ResponseEntity.ok(employeeRepository.findAll());
    }

    public ResponseEntity<Employee> getEmployee(int id) {
        Optional<Employee> employeeExist
                = employeeRepository.findById(id);
        if(employeeExist.isPresent()){
            return ResponseEntity.ok(employeeExist.get());
        } else {
            throw new IllegalArgumentException("Invalid employee id");
        }

    }

    public ResponseEntity<Employee> addNewEmployee(EmployeeModel employeeModel) {
        Employee employee = Employee.builder().employeeName(employeeModel.getEmployeeName())
                .email(employeeModel.getEmail()).build();
        return ResponseEntity.ok(employeeRepository.save(employee));
    }

    public ResponseEntity<Employee> editEmployee(int id, EmployeeModel editedEmployee) {
        Optional<Employee> employeeExist = employeeRepository.findById(id);
        Employee employee = null;

        if(employeeExist.isPresent()){
            employee = employeeExist.get();
            // If existing employee is found by ID change its details with the details provided in request.
            employee = Employee.builder().employeeId(id).employeeName(editedEmployee.getEmployeeName())
                    .email(editedEmployee.getEmail()).build();
        } else {
            throw new IllegalArgumentException("Incorrect Employee Id ");
        }


        return ResponseEntity.ok(employeeRepository.save(employee));


    }

    public ResponseEntity<?> deleteEmplyee(int id) {
        Optional<Employee> employeeExist = employeeRepository.findById(id);
        Employee employee = null;

        if(employeeExist.isPresent()){
            employee = employeeExist.get();
            // If existing employee is found by ID change its details with the details provided in request.
           employeeRepository.delete(employee);
           return ResponseEntity.ok("Employee with id "+ id +" is Deleted");
        } else {
            throw new IllegalArgumentException("Incorrect Employee Id ");
        }
    }
}
