package com.claesson.spbe.service;

import com.claesson.spbe.model.Scene;
import com.claesson.spbe.repository.postgres.SceneRepositoryPG;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SceneService {

  private final SceneRepositoryPG sceneRepositoryPG;

  public SceneService(SceneRepositoryPG sceneRepositoryPG) {
    this.sceneRepositoryPG = sceneRepositoryPG;
  }

  public List<Scene> getAllScenes() {
    return sceneRepositoryPG.findAll();
  }

  public Scene getSceneById(Long id) {
    Scene scene = sceneRepositoryPG.findById(id).orElseThrow();
    return scene;
  }
}
