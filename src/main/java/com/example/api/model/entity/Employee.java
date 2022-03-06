package com.example.api.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String phone;

    private String address;

    @NotNull
    private String idc;

    @NotNull
    private String sex;

    @JsonIgnoreProperties({"employeeList"})
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    private Storage storage;


}
