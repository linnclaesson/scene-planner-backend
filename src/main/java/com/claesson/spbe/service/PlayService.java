package com.claesson.spbe.service;

import com.claesson.spbe.model.Act;
import com.claesson.spbe.model.Play;
import com.claesson.spbe.repository.postgres.PlayRepositoryPG;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PlayService {

  private final PlayRepositoryPG playRepositoryPG;

  public PlayService(PlayRepositoryPG playRepositoryPG) {
    this.playRepositoryPG = playRepositoryPG;
  }

  public List<Play> getAllPlays() {
    return playRepositoryPG.findAll();
  }

  public Play createPlay(Play play) {
    if (play.getActs() != null) {
      for (Act act : play.getActs()) {
        act.setPlay(play);
      }
    }
    return playRepositoryPG.save(play);
  }
}
