package com.claesson.spbe.controller;

import com.claesson.spbe.model.Act;
import com.claesson.spbe.model.Play;
import com.claesson.spbe.model.Role;
import com.claesson.spbe.service.PlayService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/play")
public class PlayController {

  private final PlayService playService;

  public PlayController(PlayService playService) {
    this.playService = playService;
  }

  @GetMapping
  public ResponseEntity<List<Play>> getAllPlays() {
    return ResponseEntity.ok(playService.getAllPlays());
  }

  @GetMapping("/{id}")
  public Play getPlayById(@PathVariable Long id) {
    return playService.getPlayById(id);
  }

  @GetMapping("/{id}/acts")
  public List<Act> getAllActsByPlayId(@PathVariable Long id) {
    Play play = playService.getPlayById(id);
    return play.getActs();
  }

  @GetMapping("/{id}/roles")
  public List<Role> getAllRolesByPlayId(@PathVariable Long id) {
    Play play = playService.getPlayById(id);
    return play.getRoles();
  }

  @PostMapping(consumes = {"application/json", "application/json;charset=UTF-8"})
  public ResponseEntity<Play> createPlay(@RequestBody Play play) {
    Play created = playService.createPlay(play);
    return ResponseEntity.ok(created);
  }
}
