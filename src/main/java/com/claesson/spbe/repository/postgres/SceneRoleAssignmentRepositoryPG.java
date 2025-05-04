package com.claesson.spbe.repository.postgres;

import com.claesson.spbe.model.SceneRoleAssignment;
import com.claesson.spbe.model.SceneRoleAssignmentKey;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SceneRoleAssignmentRepositoryPG
    extends JpaRepository<SceneRoleAssignment, SceneRoleAssignmentKey> {

  List<SceneRoleAssignment> findBySceneId(Long sceneId);
}
