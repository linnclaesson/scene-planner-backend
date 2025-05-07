package com.claesson.spbe.repository.postgres;

import com.claesson.spbe.model.Scene;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SceneRepositoryPG extends JpaRepository<Scene, Long> {
  List<Scene> findByAct_Play_Id(Long playId);
}
