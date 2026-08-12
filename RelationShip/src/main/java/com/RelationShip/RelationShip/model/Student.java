package com.RelationShip.RelationShip.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stud_id;

    @Column(nullable = false)
    private String stud_name;

//    @ManyToOne(optional = false) This thing will make sure that a relation should exist in java
//    @JoinColumn(name = "dept_id", nullable = false) This thing will make sure that a relationship should exist in the database
    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;
}
