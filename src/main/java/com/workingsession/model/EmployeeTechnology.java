package com.workingsession.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee_technology")
public class EmployeeTechnology implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "employee_id")
    private Integer employeeId;
    @Column(name = "technology_id")
    private Integer technologyId;
    @Column(name = "expertise_level")
    private String expertiseLevel;
}
