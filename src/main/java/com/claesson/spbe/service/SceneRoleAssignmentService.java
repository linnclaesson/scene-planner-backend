package com.claesson.spbe.service;

import com.claesson.spbe.dto.SceneRoleAssignmentDTO;
import com.claesson.spbe.model.Actor;
import com.claesson.spbe.model.Role;
import com.claesson.spbe.model.Scene;
import com.claesson.spbe.model.SceneRoleAssignment;
import com.claesson.spbe.model.SceneRoleAssignmentKey;
import com.claesson.spbe.repository.postgres.ActorRepositoryPG;
import com.claesson.spbe.repository.postgres.RoleRepositoryPG;
import com.claesson.spbe.repository.postgres.SceneRepositoryPG;
import com.claesson.spbe.repository.postgres.SceneRoleAssignmentRepositoryPG;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SceneRoleAssignmentService {

  private final SceneRoleAssignmentRepositoryPG sceneRoleAssignmentRepositoryPG;
  private final SceneRepositoryPG sceneRepositoryPG;
  private final ActorRepositoryPG actorRepositoryPG;
  private final RoleRepositoryPG roleRepositoryPG;

  public SceneRoleAssignmentService(
      SceneRoleAssignmentRepositoryPG sceneRoleAssignmentRepositoryPG,
      SceneRepositoryPG sceneRepositoryPG,
      ActorRepositoryPG actorRepositoryPG,
      RoleRepositoryPG roleRepositoryPG) {
    this.sceneRoleAssignmentRepositoryPG = sceneRoleAssignmentRepositoryPG;
    this.sceneRepositoryPG = sceneRepositoryPG;
    this.actorRepositoryPG = actorRepositoryPG;
    this.roleRepositoryPG = roleRepositoryPG;
  }

  public List<SceneRoleAssignment> getAllAssignments() {
    return sceneRoleAssignmentRepositoryPG.findAll();
  }

  public List<SceneRoleAssignment> getAssignmentsBySceneId(Long sceneId) {
    return sceneRoleAssignmentRepositoryPG.findBySceneId(sceneId);
  }

  public SceneRoleAssignment createAssignment(SceneRoleAssignment sceneRoleAssignment) {
    return sceneRoleAssignmentRepositoryPG.save(sceneRoleAssignment);
  }

  public void deleteAssignment(SceneRoleAssignmentKey id) {
    sceneRoleAssignmentRepositoryPG.deleteById(id);
  }

  public SceneRoleAssignment mapDtoToEntity(SceneRoleAssignmentDTO sceneRoleAssignmentDto) {
    Scene scene =
        sceneRepositoryPG
            .findById(sceneRoleAssignmentDto.getSceneId())
            .orElseThrow(() -> new RuntimeException("Scene not found"));
    Actor actor =
        actorRepositoryPG
            .findById(sceneRoleAssignmentDto.getActorId())
            .orElseThrow(() -> new RuntimeException("Actor not found"));
    Role role =
        roleRepositoryPG
            .findById(sceneRoleAssignmentDto.getRoleId())
            .orElseThrow(() -> new RuntimeException("Role not found"));
    return new SceneRoleAssignment(scene, actor, role);
  }
}
