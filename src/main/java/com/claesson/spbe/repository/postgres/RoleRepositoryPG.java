package com.claesson.spbe.repository.postgres;

import com.claesson.spbe.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepositoryPG extends JpaRepository<Role, Long> {}
