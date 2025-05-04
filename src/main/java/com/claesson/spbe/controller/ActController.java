package com.claesson.spbe.controller;

import com.claesson.spbe.model.Act;
import com.claesson.spbe.model.Scene;
import com.claesson.spbe.service.ActService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/act")
public class ActController {

  private final ActService actService;

  public ActController(ActService actService) {
    this.actService = actService;
  }

  @GetMapping
  public ResponseEntity<List<Act>> getAllActs() {
    return ResponseEntity.ok(actService.getAllActs());
  }

  @GetMapping("/{id}")
  public Act getActById(@PathVariable Long id) {
    return actService.getActById(id);
  }

  @GetMapping("/{id}/scenes")
  public List<Scene> getAllScenesByActId(@PathVariable Long id) {
    Act act = actService.getActById(id);
    return act.getScenes();
  }

  @PostMapping("/play/{play_id}")
  public ResponseEntity<Act> createAct(@PathVariable Long play_id, @RequestBody Act act) {
    Act createdAct = actService.createAct(act, play_id);
    return ResponseEntity.ok(createdAct);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteAct(@PathVariable Long id) {
    try {
      actService.deleteAct(id);
      return ResponseEntity.ok("Act deleted successfully!");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting act");
    }
  }
}
