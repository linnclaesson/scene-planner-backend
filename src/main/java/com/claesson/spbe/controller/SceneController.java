package com.claesson.spbe.controller;

import com.claesson.spbe.model.Scene;
import com.claesson.spbe.service.SceneService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
