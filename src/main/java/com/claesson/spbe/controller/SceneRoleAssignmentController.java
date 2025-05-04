package com.claesson.spbe.controller;

import com.claesson.spbe.dto.SceneRoleAssignmentDTO;
import com.claesson.spbe.model.SceneRoleAssignment;
import com.claesson.spbe.model.SceneRoleAssignmentKey;
import com.claesson.spbe.repository.postgres.SceneRoleAssignmentRepositoryPG;
import com.claesson.spbe.service.SceneRoleAssignmentService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assignments")
public class SceneRoleAssignmentController {

  private final SceneRoleAssignmentService sceneRoleAssignmentService;
  private final SceneRoleAssignmentRepositoryPG sceneRoleAssignmentRepositoryPG;

  public SceneRoleAssignmentController(
      SceneRoleAssignmentService sceneRoleAssignmentService,
      SceneRoleAssignmentRepositoryPG sceneRoleAssignmentRepositoryPG) {
    this.sceneRoleAssignmentService = sceneRoleAssignmentService;
    this.sceneRoleAssignmentRepositoryPG = sceneRoleAssignmentRepositoryPG;
  }

  @GetMapping
  public ResponseEntity<List<SceneRoleAssignment>> getAllAssignments() {
    return ResponseEntity.ok(sceneRoleAssignmentService.getAllAssignments());
  }

  @GetMapping("/scene/{sceneId}")
  public List<SceneRoleAssignment> getAssignmentBySceneId(@PathVariable Long sceneId) {
    return sceneRoleAssignmentService.getAssignmentsBySceneId(sceneId);
  }

  @PostMapping
  public ResponseEntity<SceneRoleAssignment> createAssignment(
      @RequestBody SceneRoleAssignmentDTO sceneRoleAssignmentDto) {
    SceneRoleAssignment assignment =
        sceneRoleAssignmentService.mapDtoToEntity(sceneRoleAssignmentDto);
    SceneRoleAssignment createdAssignment = sceneRoleAssignmentRepositoryPG.save(assignment);
    return ResponseEntity.ok(createdAssignment);
  }

  @DeleteMapping("/{sceneId}/{actorId}/{roleId}")
  public ResponseEntity<?> delete(
      @PathVariable Long sceneId, @PathVariable Long actorId, @PathVariable Long roleId) {
    SceneRoleAssignmentKey key = new SceneRoleAssignmentKey(sceneId, actorId, roleId);
    sceneRoleAssignmentService.deleteAssignment(key);
    return ResponseEntity.ok("Assignment deleted successfully!");
  }
}
