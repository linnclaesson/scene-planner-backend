package com.claesson.spbe.repository.postgres;

import com.claesson.spbe.model.Rehearsal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RehearsalRepositoryPG extends JpaRepository<Rehearsal, Long> {}
