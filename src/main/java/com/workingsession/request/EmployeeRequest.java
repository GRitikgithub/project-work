package com.workingsession.request;

import lombok.Data;

@Data
public class EmployeeRequest {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Character gender;
}
