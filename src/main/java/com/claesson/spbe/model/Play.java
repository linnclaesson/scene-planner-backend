package com.claesson.spbe.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "play")
public class Play {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @OneToMany(mappedBy = "play", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<Act> acts = new ArrayList<>();

  @OneToMany(mappedBy = "play", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<Role> roles = new ArrayList<>();

  // Constructors

  public Play() {}

  public Play(String title) {
    this.title = title;
  }

  // Getters &

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<Act> getActs() {
    return acts;
  }

  public void setActs(List<Act> acts) {
    this.acts = acts;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  // Methods

  public void addAct(Act act) {
    this.acts.add(act);
    act.setPlay(this);
  }

  public void addRole(Role role) {
    this.roles.add(role);
    role.setPlay(this);
  }
}
