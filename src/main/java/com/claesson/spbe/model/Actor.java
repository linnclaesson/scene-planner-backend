package com.claesson.spbe.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "actor")
public class Actor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToMany(mappedBy = "actor", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<Role> roles = new ArrayList<>();

  // Constructors
  public Actor() {}

  public Actor(String name) {
    this.name = name;
  }

  // Getters & setters

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  // Methods
  public void addRole(Role role) {
    this.roles.add(role);
    role.setActor(this);
  }
}
