package com.techutube.employee.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeModel {
    private String employeeName;
    private String email;
}
