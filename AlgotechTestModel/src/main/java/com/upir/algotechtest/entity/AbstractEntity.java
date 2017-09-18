package com.upir.algotechtest.entity;

import java.util.List;

import javax.persistence.*;

 
@MappedSuperclass
public class AbstractEntity {
  @Id
  //@SequenceGenerator(name = "FIRST_JPA_GEN", sequenceName = "FIRST_JPA_SEQ", allocationSize=1, initialValue=1000)
  //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FIRST_JPA_GEN")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
 
 
  public Long getId() {
    return id;
  }
  
  
}