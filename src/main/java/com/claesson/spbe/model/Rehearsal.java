package com.claesson.spbe.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rehearsal")
public class Rehearsal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate date;

  @ManyToMany
  @JsonManagedReference
  @JoinTable(
      name = "rehearsal_scene",
      joinColumns = @JoinColumn(name = "rehearsal_id"),
      inverseJoinColumns = @JoinColumn(name = "scene_id"))
  private List<Scene> scenes = new ArrayList<>();

  // Constructors

  public Rehearsal() {}

  public Rehearsal(LocalDate date) {
    this.date = date;
  }

  public Rehearsal(LocalDate date, List<Scene> scenes) {
    this.date = date;
    this.scenes = scenes;
  }

  // Getters & setters

  public Long getId() {
    return id;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public List<Scene> getScenes() {
    return scenes;
  }

  public void setScenes(List<Scene> scenes) {
    this.scenes = scenes;
  }

  // Methods

  public void addScene(Scene scene) {
    if (!this.scenes.contains(scene)) {
      this.scenes.add(scene);
      scene.getRehearsals().add(this);
    }
  }

  public void removeScene(Scene scene) {
    if (this.scenes.contains(scene)) {
      this.scenes.remove(scene);
      scene.getRehearsals().remove(this);
    }
  }
}
