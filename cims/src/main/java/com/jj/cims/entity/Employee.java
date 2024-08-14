package com.jj.cims.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonInclude(JsonInclude.Include.NON_NULL)

@Inheritance(strategy = InheritanceType.JOINED) // ou TABLE_PER
public class Employee {
    @Id

    private Integer matricule;
    private String nom;
    private String password;
    private String prenom;

    private int  nbreenfant;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enfant> enfants;

}
