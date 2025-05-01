package com.claesson.spbe.repository.postgres;

import com.claesson.spbe.model.Play;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayRepositoryPG extends JpaRepository<Play, Long> {}
