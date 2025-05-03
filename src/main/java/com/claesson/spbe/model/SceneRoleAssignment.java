package com.claesson.spbe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "sceneRoleAssignment")
public class SceneRoleAssignment {

  @EmbeddedId private SceneRoleAssignmentKey id;

  @ManyToOne
  @MapsId("sceneId")
  @JsonBackReference("scene-assignment")
  private Scene scene;

  @ManyToOne
  @MapsId("actorId")
  private Actor actor;

  @ManyToOne
  @MapsId("roleId")
  private Role role;

  public SceneRoleAssignment() {}

  public SceneRoleAssignment(Scene scene, Actor actor, Role role) {
    this.scene = scene;
    this.actor = actor;
    this.role = role;
    this.id = new SceneRoleAssignmentKey(scene.getId(), actor.getId(), role.getId());
  }

  // Getters & setters

  public SceneRoleAssignmentKey getId() {
    return id;
  }

  public void setId(SceneRoleAssignmentKey id) {
    this.id = id;
  }

  public Scene getScene() {
    return scene;
  }

  public void setScene(Scene scene) {
    this.scene = scene;
  }

  public Actor getActor() {
    return actor;
  }

  public void setActor(Actor actor) {
    this.actor = actor;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
