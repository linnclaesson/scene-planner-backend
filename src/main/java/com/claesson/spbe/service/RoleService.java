package com.claesson.spbe.service;

import com.claesson.spbe.model.Actor;
import com.claesson.spbe.model.Role;
import com.claesson.spbe.repository.postgres.ActorRepositoryPG;
import com.claesson.spbe.repository.postgres.RoleRepositoryPG;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

  private final RoleRepositoryPG roleRepositoryPG;
  private final ActorRepositoryPG actorRepositoryPG;

  public RoleService(RoleRepositoryPG roleRepositoryPG, ActorRepositoryPG actorRepositoryPG) {
    this.roleRepositoryPG = roleRepositoryPG;
    this.actorRepositoryPG = actorRepositoryPG;
  }

  public List<Role> getAllRoles() {
    return roleRepositoryPG.findAll();
  }

  public Role getRoleById(Long id) {
    Role role = roleRepositoryPG.findById(id).orElseThrow();
    return role;
  }

  public Role createRole(Role role, Long actor_id) {
    Actor actor = actorRepositoryPG.findById(actor_id).orElseThrow();
    role.setActor(actor);
    return roleRepositoryPG.save(role);
  }

  public void deleteRole(Long id) {
    Role role = roleRepositoryPG.findById(id).orElseThrow();
    roleRepositoryPG.delete(role);
  }
}
