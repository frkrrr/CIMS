package com.jj.cims.entity;


import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
public class Enfant {

    @Id
    @GeneratedValue
    private int id;

    private String nom;

    private String prenom;

    private String ns;

    private int age;


    @ManyToOne
    private Employee employee ;
}