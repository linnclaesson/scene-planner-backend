package com.claesson.spbe.service;

import com.claesson.spbe.model.Play;
import com.claesson.spbe.model.Role;
import com.claesson.spbe.repository.postgres.PlayRepositoryPG;
import com.claesson.spbe.repository.postgres.RoleRepositoryPG;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

  private final RoleRepositoryPG roleRepositoryPG;
  private final PlayRepositoryPG playRepositoryPG;

  public RoleService(RoleRepositoryPG roleRepositoryPG, PlayRepositoryPG playRepositoryPG) {
    this.roleRepositoryPG = roleRepositoryPG;
    this.playRepositoryPG = playRepositoryPG;
  }

  public List<Role> getAllRoles() {
    return roleRepositoryPG.findAll();
  }

  public Role getRoleById(Long id) {
    Role role = roleRepositoryPG.findById(id).orElseThrow();
    return role;
  }

  public Role createRole(Role role, Long play_id) {
    Play play = playRepositoryPG.findById(play_id).orElseThrow();
    role.setPlay(play);
    return roleRepositoryPG.save(role);
  }

  public void deleteRole(Long id) {
    Role role = roleRepositoryPG.findById(id).orElseThrow();
    roleRepositoryPG.delete(role);
  }
}
