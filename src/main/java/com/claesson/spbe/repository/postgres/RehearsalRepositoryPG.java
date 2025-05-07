package com.claesson.spbe.repository.postgres;

import com.claesson.spbe.model.Rehearsal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RehearsalRepositoryPG extends JpaRepository<Rehearsal, Long> {
  List<Rehearsal> findByPlayId(Long playId);
}
