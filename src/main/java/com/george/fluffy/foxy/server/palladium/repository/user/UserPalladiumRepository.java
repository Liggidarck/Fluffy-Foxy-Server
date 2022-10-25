package com.george.fluffy.foxy.server.palladium.repository.user;

import com.george.fluffy.foxy.server.palladium.model.user.UserPalladium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPalladiumRepository extends JpaRepository<UserPalladium, Long> {

    Optional<UserPalladium> findByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

}