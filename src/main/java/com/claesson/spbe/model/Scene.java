package com.claesson.spbe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "scene")
public class Scene {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne
  @JoinColumn(name = "act_id")
  @JsonBackReference("act-scene")
  private Act act;

  @OneToMany(mappedBy = "scene", cascade = CascadeType.ALL)
  @JsonManagedReference("scene-assignment")
  private List<SceneRoleAssignment> sceneRoleAssignments = new ArrayList<>();

  @ManyToMany(mappedBy = "scenes")
  @JsonIgnore
  private List<Rehearsal> rehearsals = new ArrayList<>();

  // Constructors

  public Scene() {}

  public Scene(String name) {
    this.name = name;
  }

  public Scene(String name, Act act) {
    this.name = name;
    this.act = act;
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

  public Act getAct() {
    return act;
  }

  public void setAct(Act act) {
    this.act = act;
  }

  public List<SceneRoleAssignment> getSceneRoleAssignments() {
    return sceneRoleAssignments;
  }

  public void setSceneRoleAssignments(List<SceneRoleAssignment> sceneRoleAssignments) {
    this.sceneRoleAssignments = sceneRoleAssignments;
  }

  public List<Rehearsal> getRehearsals() {
    return rehearsals;
  }

  public void setRehearsals(List<Rehearsal> rehearsals) {
    this.rehearsals = rehearsals;
  }

  // Methods

  public void addSceneRoleAssignment(SceneRoleAssignment assignment) {
    this.sceneRoleAssignments.add(assignment);
    assignment.setScene(this);
  }

  public void addRehearsal(Rehearsal rehearsal) {
    if (!this.rehearsals.contains(rehearsal)) {
      this.rehearsals.add(rehearsal);
      rehearsal.getScenes().add(this);
    }
  }

  public void removeRehearsal(Rehearsal rehearsal) {
    if (this.rehearsals.contains(rehearsal)) {
      this.rehearsals.remove(rehearsal);
      rehearsal.getScenes().remove(this);
    }
  }
}
