package com.RelationShip.RelationShip.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    @JsonBackReference
    private Department department;
}
