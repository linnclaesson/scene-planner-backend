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
@Table(name = "play")
public class Play {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String semester;

  @OneToMany(mappedBy = "play", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference("play-act")
  private List<Act> acts = new ArrayList<>();

  @OneToMany(mappedBy = "play", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference("play-role")
  private List<Role> roles = new ArrayList<>();

  // Constructors

  public Play() {}

  public Play(String title) {
    this.title = title;
  }

  public Play(String title, String semester, List<Act> acts, List<Role> roles) {
    this.title = title;
    this.semester = semester;
    this.acts = acts != null ? acts : new ArrayList<>();
    this.roles = roles != null ? roles : new ArrayList<>();
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

  public String getSemester() {
    return semester;
  }

  public void setSemester(String semester) {
    this.semester = semester;
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
