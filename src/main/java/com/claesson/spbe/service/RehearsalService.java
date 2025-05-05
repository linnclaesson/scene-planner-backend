package com.claesson.spbe.service;

import com.claesson.spbe.model.Rehearsal;
import com.claesson.spbe.model.Scene;
import com.claesson.spbe.model.SceneRoleAssignment;
import com.claesson.spbe.repository.postgres.RehearsalRepositoryPG;
import com.claesson.spbe.repository.postgres.SceneRepositoryPG;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RehearsalService {

  private final RehearsalRepositoryPG rehearsalRepositoryPG;
  private final SceneRepositoryPG sceneRepositoryPG;

  public RehearsalService(
      RehearsalRepositoryPG rehearsalRepositoryPG, SceneRepositoryPG sceneRepositoryPG) {
    this.rehearsalRepositoryPG = rehearsalRepositoryPG;
    this.sceneRepositoryPG = sceneRepositoryPG;
  }

  public List<Rehearsal> getAllRehearsals() {
    return rehearsalRepositoryPG.findAll();
  }

  public Rehearsal getRehearsalById(Long id) {
    Rehearsal rehearsal =
        rehearsalRepositoryPG
            .findById(id)
            .orElseThrow(
                () -> new IllegalArgumentException("Could not find rehearsal with id " + id));
    return rehearsal;
  }

  public List<SceneRoleAssignment> getSceneRoleAssignmentsForRehearsal(Long id) {
    Rehearsal rehearsal = getRehearsalById(id);
    List<Scene> scenes = rehearsal.getScenes();
    List<SceneRoleAssignment> allAssignments = new ArrayList<>();
    for (Scene scene : scenes) {
      allAssignments.addAll(scene.getSceneRoleAssignments());
    }
    return allAssignments;
  }

  public Rehearsal createRehearsal(Rehearsal rehearsal) {
    return rehearsalRepositoryPG.save(rehearsal);
  }

  public void deleteRehearsal(Long id) {
    if (!rehearsalRepositoryPG.existsById(id)) {
      throw new IllegalArgumentException("Could not find rehearsal with id " + id);
    }
    rehearsalRepositoryPG.deleteById(id);
  }

  public Rehearsal addSceneToRehearsal(Long rehearsalId, Long sceneId) {
    Rehearsal rehearsal = getRehearsalById(rehearsalId);
    Scene scene =
        sceneRepositoryPG
            .findById(sceneId)
            .orElseThrow(
                () -> new IllegalArgumentException("Could not find scene with id " + sceneId));
    if (!rehearsal.getScenes().contains(scene)) {
      rehearsal.getScenes().add(scene);
    }
    return rehearsalRepositoryPG.save(rehearsal);
  }

  public Rehearsal removeSceneFromRehearsal(Long rehearsalId, Long sceneId) {
    Rehearsal rehearsal = getRehearsalById(rehearsalId);
    Scene scene =
        sceneRepositoryPG
            .findById(sceneId)
            .orElseThrow(
                () -> new IllegalArgumentException("Could not find scene with id " + sceneId));
    boolean removed = rehearsal.getScenes().remove(scene);
    if (!removed) {
      throw new IllegalArgumentException(
          "Scene with id " + sceneId + " is not assigned to rehearsal " + rehearsalId);
    }
    return rehearsalRepositoryPG.save(rehearsal);
  }
}
