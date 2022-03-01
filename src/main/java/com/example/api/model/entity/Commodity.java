package com.example.api.model.entity;

import javax.persistence.*;

import jdk.jfr.Unsigned;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Commodity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(unique = true)
    private String name;

    private double price;

    private String description;

    @Column(columnDefinition = "int unsigned")
    private int count;

    private String createAt;

    private String updateAt;

}
