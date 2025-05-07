package com.claesson.spbe.service;

import com.claesson.spbe.model.Play;
import com.claesson.spbe.model.Rehearsal;
import com.claesson.spbe.model.Scene;
import com.claesson.spbe.model.SceneRoleAssignment;
import com.claesson.spbe.repository.postgres.PlayRepositoryPG;
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
  private final PlayRepositoryPG playRepositoryPG;

  public RehearsalService(
      RehearsalRepositoryPG rehearsalRepositoryPG,
      SceneRepositoryPG sceneRepositoryPG,
      PlayRepositoryPG playRepositoryPG) {
    this.rehearsalRepositoryPG = rehearsalRepositoryPG;
    this.sceneRepositoryPG = sceneRepositoryPG;
    this.playRepositoryPG = playRepositoryPG;
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

  public List<Rehearsal> getRehearsalsByPlayId(Long playId) {
    return rehearsalRepositoryPG.findByPlayId(playId);
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

  public Rehearsal createRehearsal(Long playId, Rehearsal rehearsal) {
    Play play =
        playRepositoryPG
            .findById(playId)
            .orElseThrow(
                () -> new IllegalArgumentException("Could not find play with id " + playId));
    rehearsal.setPlay(play);
    return rehearsalRepositoryPG.save(rehearsal);
  }

  public void deleteRehearsal(Long id) {
    Rehearsal rehearsal =
        rehearsalRepositoryPG
            .findById(id)
            .orElseThrow(
                () -> new IllegalArgumentException("Could not find rehearsal with id " + id));
    Play play = rehearsal.getPlay();
    if (play != null) {
      play.getRehearsals().remove(rehearsal);
    }

    rehearsalRepositoryPG.delete(rehearsal);
  }

  public Rehearsal addSceneToRehearsal(Long rehearsalId, Long sceneId) {
    Rehearsal rehearsal = getRehearsalById(rehearsalId);
    Scene scene =
        sceneRepositoryPG
            .findById(sceneId)
            .orElseThrow(
                () -> new IllegalArgumentException("Could not find scene with id " + sceneId));

    rehearsal.addScene(scene);
    return rehearsalRepositoryPG.save(rehearsal);
  }

  public Rehearsal removeSceneFromRehearsal(Long rehearsalId, Long sceneId) {
    Rehearsal rehearsal = getRehearsalById(rehearsalId);
    Scene scene =
        sceneRepositoryPG
            .findById(sceneId)
            .orElseThrow(
                () -> new IllegalArgumentException("Could not find scene with id " + sceneId));

    rehearsal.removeScene(scene);
    return rehearsalRepositoryPG.save(rehearsal);
  }
}
