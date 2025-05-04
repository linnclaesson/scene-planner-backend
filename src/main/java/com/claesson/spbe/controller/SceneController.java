package com.claesson.spbe.controller;

import com.claesson.spbe.model.Scene;
import com.claesson.spbe.service.SceneService;
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
@RequestMapping("/scene")
public class SceneController {

  private final SceneService sceneService;

  public SceneController(SceneService sceneService) {
    this.sceneService = sceneService;
  }

  @GetMapping
  public ResponseEntity<List<Scene>> getAllScenes() {
    return ResponseEntity.ok(sceneService.getAllScenes());
  }

  @GetMapping("/{id}")
  public Scene getSceneById(@PathVariable Long id) {
    return sceneService.getSceneById(id);
  }

  @PostMapping("/act/{act_id}")
  public ResponseEntity<Scene> createScene(@PathVariable Long act_id, @RequestBody Scene scene) {
    Scene createdScene = sceneService.createScene(scene, act_id);
    return ResponseEntity.ok(createdScene);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteScene(@PathVariable Long id) {
    try {
      sceneService.deleteScene(id);
      return ResponseEntity.ok("Scene deleted successfully!");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting scene");
    }
  }
}
