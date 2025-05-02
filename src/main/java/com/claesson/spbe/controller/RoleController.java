package com.claesson.spbe.controller;

import com.claesson.spbe.model.Role;
import com.claesson.spbe.service.RoleService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

  private final RoleService roleService;

  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }

  @GetMapping
  public ResponseEntity<List<Role>> getAllRoles() {
    return ResponseEntity.ok(roleService.getAllRoles());
  }

  @GetMapping("/{id}")
  public Role getRoleById(@PathVariable Long id) {
    return roleService.getRoleById(id);
  }

  @PostMapping("/actor/{actor_id}")
  public ResponseEntity<Role> createRole(@PathVariable Long actor_id, @RequestBody Role role) {
    Role createdRole = roleService.createRole(role, actor_id);
    return ResponseEntity.ok(createdRole);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteRole(@PathVariable Long id) {
    try {
      roleService.deleteRole(id);
      return ResponseEntity.status(HttpStatus.CREATED).body("Role deleted successfully");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting role");
    }
  }
}
