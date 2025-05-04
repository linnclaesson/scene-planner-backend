package com.claesson.spbe.service;

import com.claesson.spbe.model.Act;
import com.claesson.spbe.model.Scene;
import com.claesson.spbe.repository.postgres.ActRepositoryPG;
import com.claesson.spbe.repository.postgres.SceneRepositoryPG;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SceneService {

  private final SceneRepositoryPG sceneRepositoryPG;
  private final ActRepositoryPG actRepositoryPG;

  public SceneService(SceneRepositoryPG sceneRepositoryPG, ActRepositoryPG actRepositoryPG) {
    this.sceneRepositoryPG = sceneRepositoryPG;
    this.actRepositoryPG = actRepositoryPG;
  }

  public List<Scene> getAllScenes() {
    return sceneRepositoryPG.findAll();
  }

  public Scene getSceneById(Long id) {
    Scene scene =
        sceneRepositoryPG
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Scene not found!"));
    return scene;
  }

  public Scene createScene(Scene scene, Long act_id) {
    Act act =
        actRepositoryPG
            .findById(act_id)
            .orElseThrow(
                () -> new IllegalArgumentException("A scene must belong to an existing act"));
    scene.setAct(act);
    return sceneRepositoryPG.save(scene);
  }

  public void deleteScene(Long id) {
    if (!sceneRepositoryPG.existsById(id)) {
      throw new IllegalArgumentException("Scene with id " + id + " not found");
    }
    sceneRepositoryPG.deleteById(id);
  }
}
