package com.claesson.spbe.repository.postgres;

import com.claesson.spbe.model.Act;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActRepositoryPG extends JpaRepository<Act, Long> {}
