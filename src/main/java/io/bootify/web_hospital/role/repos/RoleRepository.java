package io.bootify.web_hospital.role.repos;

import io.bootify.web_hospital.role.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Integer> {
}
