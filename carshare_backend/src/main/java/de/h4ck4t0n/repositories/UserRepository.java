package de.h4ck4t0n.repositories;

import de.h4ck4t0n.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by: saschabast
 * since: 04/11/2016
 */
public interface UserRepository extends JpaRepository<User,Long> {
}
