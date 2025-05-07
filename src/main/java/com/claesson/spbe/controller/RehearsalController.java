package com.claesson.spbe.controller;

import com.claesson.spbe.model.Rehearsal;
import com.claesson.spbe.model.SceneRoleAssignment;
import com.claesson.spbe.service.RehearsalService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rehearsal")
public class RehearsalController {

  public final RehearsalService rehearsalService;

  public RehearsalController(RehearsalService rehearsalService) {
    this.rehearsalService = rehearsalService;
  }

  @GetMapping
  public ResponseEntity<List<Rehearsal>> getAllRehearsals() {
    return ResponseEntity.ok(rehearsalService.getAllRehearsals());
  }

  @GetMapping("/{id}")
  public Rehearsal getRehearsalById(@PathVariable Long id) {
    return rehearsalService.getRehearsalById(id);
  }

  @GetMapping("/play/{playId}")
  public List<Rehearsal> getRehearsalsByPlayId(@PathVariable Long playId) {
    return rehearsalService.getRehearsalsByPlayId(playId);
  }

  @GetMapping("/{id}/assignments")
  public ResponseEntity<List<SceneRoleAssignment>> getSceneRoleAssignments(@PathVariable Long id) {
    List<SceneRoleAssignment> assignments =
        rehearsalService.getSceneRoleAssignmentsForRehearsal(id);
    return ResponseEntity.ok(assignments);
  }

  @PostMapping("/play/{playId}")
  public ResponseEntity<Rehearsal> createRehearsal(
      @PathVariable Long playId, @RequestBody Rehearsal rehearsal) {
    Rehearsal createdRehearsal = rehearsalService.createRehearsal(playId, rehearsal);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdRehearsal);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteRehearsal(@PathVariable Long id) {
    try {
      rehearsalService.deleteRehearsal(id);
      return ResponseEntity.ok("Rehearsal deleted successfully!");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error deleting rehearsal");
    }
  }

  @PutMapping("/{rehearsal_id}/scene/{scene_id}")
  public ResponseEntity<Rehearsal> addSceneToRehearsal(
      @PathVariable Long rehearsal_id, @PathVariable Long scene_id) {
    Rehearsal updatedRehearsal = rehearsalService.addSceneToRehearsal(rehearsal_id, scene_id);
    return ResponseEntity.ok(updatedRehearsal);
  }

  @DeleteMapping("/{rehearsal_id}/scene/{scene_id}")
  public ResponseEntity<?> removeSceneFromRehearsal(
      @PathVariable Long rehearsal_id, @PathVariable Long scene_id) {
    rehearsalService.removeSceneFromRehearsal(rehearsal_id, scene_id);
    return ResponseEntity.ok("Removed scene from rehearsal successfully!");
  }
}
