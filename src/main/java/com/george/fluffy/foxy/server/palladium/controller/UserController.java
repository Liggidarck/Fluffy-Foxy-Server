package com.george.fluffy.foxy.server.palladium.controller;

import com.george.fluffy.foxy.server.auth.request.SignupRequest;
import com.george.fluffy.foxy.server.auth.response.MessageResponse;
import com.george.fluffy.foxy.server.palladium.model.user.ERole;
import com.george.fluffy.foxy.server.palladium.model.user.Role;
import com.george.fluffy.foxy.server.palladium.model.user.UserPalladium;
import com.george.fluffy.foxy.server.palladium.repository.user.RoleRepository;
import com.george.fluffy.foxy.server.palladium.repository.user.UserPalladiumRepository;
import com.george.fluffy.foxy.server.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/palladium/users")
public class UserController {

    @Autowired
    UserPalladiumRepository userPalladiumRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/get/all")
    @PreAuthorize("hasRole('ROLE_DEVELOPER')")
    public List<UserPalladium> getAllUsers() {
        return userPalladiumRepository.findAll();
    }

    @GetMapping("/get/all/roles")
    @PreAuthorize("hasRole('ROLE_DEVELOPER')")
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @GetMapping("/get/all/users")
    @PreAuthorize("hasRole('ROLE_DEVELOPER')")
    public List<UserPalladium> getUsersByRoleName(@RequestParam("name") ERole name) {
        return userPalladiumRepository.findByRoles_Name(name);
    }

    @GetMapping("/get/user")
    @PreAuthorize("hasRole('ROLE_DEVELOPER')")
    public UserPalladium getUserById(@RequestParam(value = "id") long id) {
        return userPalladiumRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + id + " not found"));
    }

    @PutMapping("/edit")
    @PreAuthorize("hasRole('ROLE_DEVELOPER')")
    public ResponseEntity<?> editUser(@RequestBody SignupRequest user,
                                      @RequestParam(value = "id") long id) {

        UserPalladium updateUser = userPalladiumRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + id + " not found"));

        updateUser.setName(user.getName());
        updateUser.setLastName(user.getLastName());
        updateUser.setPatronymic(user.getPatronymic());
        updateUser.setPassword(encoder.encode(user.getPassword()));
        updateUser.setUsername(user.getUsername());
        updateUser.setEmail(user.getEmail());
        updateUser.setZone(user.getZone());

        Set<String> strRoles = user.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ROLE_DEVELOPER":
                        Role developerRole = roleRepository.findByName(ERole.ROLE_DEVELOPER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(developerRole);

                        break;

                    case "ROLE_ADMIN":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;

                    case "ROLE_EXECUTOR":
                        Role executorRole = roleRepository.findByName(ERole.ROLE_EXECUTOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(executorRole);

                        break;

                    case "ROLE_USER":
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(userRole);

                        break;
                    default:
                        Role defaultRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(defaultRole);
                }
            });
        }

        updateUser.setRoles(roles);
        userPalladiumRepository.save(updateUser);

        return ResponseEntity.ok(new MessageResponse("User successfully edited"));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_DEVELOPER')")
    public ResponseEntity<?> deleteUser(@RequestParam(value = "id") long id) {
        userPalladiumRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + id + " not found")
        );

        userPalladiumRepository.deleteById(id);

        return ResponseEntity.ok(new MessageResponse("User successfully deleted"));
    }

}
