package org.cenfo.tareacrud.rest.admin;

import org.cenfo.tareacrud.logic.entity.rol.Role;
import org.cenfo.tareacrud.logic.entity.rol.RoleEnum;
import org.cenfo.tareacrud.logic.entity.rol.RoleRepository;
import org.cenfo.tareacrud.logic.entity.user.User;
import org.cenfo.tareacrud.logic.entity.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/admin")
@RestController
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/crearAdministrador")
    @PreAuthorize("hasRole('SUPER-ADMIN-ROLE')")
    public User createAdministrator(@RequestBody User newAdminUser) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.SUPER_ADMIN_ROLE);

        if (optionalRole.isEmpty()) {
            return null;
        }

        var user = new User();
        user.setName(newAdminUser.getName());
        user.setEmail(newAdminUser.getEmail());
        user.setPassword(passwordEncoder.encode(newAdminUser.getPassword()));
        user.setRole(optionalRole.get());

        return userRepository.save(user);
    }
    @PostMapping("/crearUsuario")
    @PreAuthorize("hasRole('USER')")
    public User createUser(@RequestBody User newUser) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);

        if (optionalRole.isEmpty()) {
            return null;
        }

        var user = new User();
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setRole(optionalRole.get());

        return userRepository.save(user);
    }
}
