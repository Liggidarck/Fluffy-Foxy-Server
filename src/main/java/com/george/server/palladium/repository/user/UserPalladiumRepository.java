package com.george.server.palladium.repository.user;

import com.george.server.palladium.model.user.ERole;
import com.george.server.palladium.model.user.UserPalladium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPalladiumRepository extends JpaRepository<UserPalladium, Long> {

    Optional<UserPalladium> findByUsername(String username);

    List<UserPalladium> findByRoles_Name(ERole name);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

}