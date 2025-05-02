package com.claesson.spbe.repository.postgres;

import com.claesson.spbe.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepositoryPG extends JpaRepository<Actor, Long> {}
