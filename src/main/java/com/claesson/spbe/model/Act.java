package com.claesson.spbe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "act")
public class Act {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @ManyToOne
  @JoinColumn(name = "play_id")
  @JsonBackReference("play-act")
  private Play play;

  @OneToMany(mappedBy = "act", cascade = CascadeType.ALL)
  @JsonManagedReference("act-scene")
  private List<Scene> scenes = new ArrayList<>();

  // Constructors

  public Act() {}

  public Act(String title, Play play) {
    this.title = title;
    this.play = play;
  }

  // Getters & Setters
  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Play getPlay() {
    return play;
  }

  public void setPlay(Play play) {
    this.play = play;
  }

  public List<Scene> getScenes() {
    return scenes;
  }

  public void setScenes(List<Scene> scenes) {
    this.scenes = scenes;
  }

  public void addScene(Scene scene) {
    this.scenes.add(scene);
    scene.setAct(this);
  }
}
