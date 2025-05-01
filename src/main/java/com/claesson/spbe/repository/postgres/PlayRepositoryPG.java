package com.claesson.spbe.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claesson.spbe.model.Play;

@Repository
public interface PlayRepositoryPG extends JpaRepository<Play, Long> {

}
