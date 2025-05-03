package com.claesson.spbe.model;

import java.io.Serializable;
import java.util.Objects;

public class SceneRoleAssignmentKey implements Serializable {

  private Long sceneId;
  private Long actorId;
  private Long roleId;

  public SceneRoleAssignmentKey() {}

  public SceneRoleAssignmentKey(Long sceneId, Long actorId, Long roleId) {
    this.sceneId = sceneId;
    this.actorId = actorId;
    this.roleId = roleId;
  }

  // Getters and setters
  public Long getSceneId() {
    return sceneId;
  }

  public void setSceneId(Long sceneId) {
    this.sceneId = sceneId;
  }

  public Long getActorId() {
    return actorId;
  }

  public void setActorId(Long actorId) {
    this.actorId = actorId;
  }

  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof SceneRoleAssignmentKey)) return false;
    SceneRoleAssignmentKey that = (SceneRoleAssignmentKey) o;
    return Objects.equals(sceneId, that.sceneId)
        && Objects.equals(actorId, that.actorId)
        && Objects.equals(roleId, that.roleId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sceneId, actorId, roleId);
  }
}
