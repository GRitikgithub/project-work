package com.workingsession.response;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class EmployeeResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Character gender;
}
