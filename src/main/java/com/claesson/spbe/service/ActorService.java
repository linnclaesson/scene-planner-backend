package com.claesson.spbe.service;

import com.claesson.spbe.model.Actor;
import com.claesson.spbe.model.Role;
import com.claesson.spbe.repository.postgres.ActorRepositoryPG;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

  private final ActorRepositoryPG actorRepositoryPG;

  public ActorService(ActorRepositoryPG actorRepositoryPG) {
    this.actorRepositoryPG = actorRepositoryPG;
  }

  public List<Actor> getAllActors() {
    return actorRepositoryPG.findAll();
  }

  public Actor getActorById(Long id) {
    Actor actor = actorRepositoryPG.findById(id).orElseThrow();
    return actor;
  }

  public Actor createActor(Actor actor) {
    if (actor.getRoles() != null) {
      for (Role role : actor.getRoles()) {
        role.setActor(actor);
      }
    }
    return actorRepositoryPG.save(actor);
  }

  public void deleteActor(Long id) {
    Actor actor = actorRepositoryPG.findById(id).orElseThrow();

    for (Role role : actor.getRoles()) {
      role.setActor(null);
    }

    actor.setRoles(null);
    actorRepositoryPG.delete(actor);
  }
}
