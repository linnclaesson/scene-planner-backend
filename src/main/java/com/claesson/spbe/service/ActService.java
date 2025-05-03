package com.claesson.spbe.service;

import com.claesson.spbe.model.Act;
import com.claesson.spbe.model.Play;
import com.claesson.spbe.model.Scene;
import com.claesson.spbe.repository.postgres.ActRepositoryPG;
import com.claesson.spbe.repository.postgres.PlayRepositoryPG;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ActService {

  private final ActRepositoryPG actRepositoryPG;
  private final PlayRepositoryPG playRepositoryPG;

  public ActService(ActRepositoryPG actRepositoryPG, PlayRepositoryPG playRepositoryPG) {
    this.actRepositoryPG = actRepositoryPG;
    this.playRepositoryPG = playRepositoryPG;
  }

  public List<Act> getAllActs() {
    return actRepositoryPG.findAll();
  }

  public Act getActById(Long id) {
    Act act = actRepositoryPG.findById(id).orElseThrow();
    return act;
  }

  public Act createAct(Act act, Long play_id) {
    Play play = playRepositoryPG.findById(play_id).orElseThrow();
    act.setPlay(play);
    return actRepositoryPG.save(act);
  }

  public void deleteAct(Long id) {
    Act act = actRepositoryPG.findById(id).orElseThrow();

    for (Scene scene : act.getScenes()) {
      scene.setAct(null);
    }

    act.setScenes(null);
    actRepositoryPG.delete(act);
  }
}
