package com.techutube.employee.controller;

import com.techutube.employee.entity.Employee;
import com.techutube.employee.model.EmployeeModelJdbcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employee/jdbc")
public class EmployeeJdbcClient {


    @Autowired
    JdbcClient jdbcClient;

    @PostMapping(path = "/create")
    public int addEmployee(@RequestBody EmployeeModelJdbcClient employeeModelJdbcClient){
        return jdbcClient.sql("Insert into employee (employee_id,employee_name, email) values (?,?,?)")
                .params(List.of(employeeModelJdbcClient.getEmployeeId(),
                        employeeModelJdbcClient.getEmployeeName(),
                        employeeModelJdbcClient.getEmail()))
                .update();
    }

    @GetMapping(path =  "/get")
    public List<Employee> getAllEmployee(){
        return jdbcClient.sql("select * from employee")
                .query(Employee.class)
                .list();
    }

    @GetMapping(path =  "/get/{id}")
    public Employee getAllEmployee(@PathVariable int id){
        return jdbcClient.sql("select * from employee where employee_id = :id")
                .param("id", id)
                .query(Employee.class)
                .optional().get();
    }

    @PutMapping(path =  "/update/{id}")
    public int updateEmployee(@PathVariable int id,@RequestBody EmployeeModelJdbcClient employeeModelJdbcClient){
        return jdbcClient.sql("update employee set employee_name = ?, email = ? where employee_id = ?")
                .params(employeeModelJdbcClient.getEmployeeName(), employeeModelJdbcClient.getEmail(), id)
                .update();
    }

    @DeleteMapping(path =  "/delete/{id}")
    public int deleteEmployee(@PathVariable int id){
        return jdbcClient.sql("delete from employee where employee_id = ?")
                .params(id)
                .update();
    }


}
