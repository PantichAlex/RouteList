package ru.pantich.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pantich.domain.UserRole;

public interface RoleRepo extends JpaRepository<UserRole, Long> {
        UserRole findByRole(String role);
}
