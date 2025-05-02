package com.claesson.spbe.controller;

import com.claesson.spbe.model.Actor;
import com.claesson.spbe.model.Role;
import com.claesson.spbe.service.ActorService;
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
@RequestMapping("/actor")
public class ActorController {

  private final ActorService actorService;

  public ActorController(ActorService actorService) {
    this.actorService = actorService;
  }

  @GetMapping
  public ResponseEntity<List<Actor>> getAllActors() {
    return ResponseEntity.ok(actorService.getAllActors());
  }

  @GetMapping("/{id}")
  public Actor getActorById(@PathVariable Long id) {
    return actorService.getActorById(id);
  }

  @GetMapping("/{id}/roles")
  public List<Role> getAllRolesByActorId(@PathVariable Long id) {
    Actor actor = actorService.getActorById(id);
    return actor.getRoles();
  }

  @PostMapping
  public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
    Actor createdActor = actorService.createActor(actor);
    return ResponseEntity.ok(createdActor);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteActor(@PathVariable Long id) {
    try {
      actorService.deleteActor(id);
      return ResponseEntity.status(HttpStatus.CREATED).body("Actor deleted successfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting actor");
    }
  }
}
