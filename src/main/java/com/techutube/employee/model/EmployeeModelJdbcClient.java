package com.techutube.employee.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeModelJdbcClient {
    private int employeeId;
    private String employeeName;
    private String email;
}
