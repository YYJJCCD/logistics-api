package com.example.api.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Storage {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(unique = true)
    private String storageName;

    private String leaderName;

    private String createAt;

    @JsonIgnoreProperties({"storage"})
    @OneToMany(mappedBy = "storage", cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<Employee> employeeList;

}
