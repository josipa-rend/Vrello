package com.jrendulic.Vrello.service;

import com.jrendulic.Vrello.exception.EntityNotFoundException;
import com.jrendulic.Vrello.model.Role;
import com.jrendulic.Vrello.model.User;
import com.jrendulic.Vrello.repo.RoleRepo;
import com.jrendulic.Vrello.repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByUsername(username)
                .orElseThrow( () -> new EntityNotFoundException("User by username " + username + " was not found"));
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(), user.getPassword(), authorities
        );
    }

    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }

    public User findUserById(Long id) {
        return userRepo.findUserById(id)
                .orElseThrow( () -> new EntityNotFoundException("Employee by id " + id + " was not found"));
    }

    public void deleteUser(Long id) {
        userRepo.deleteUserById(id);
    }

    public Role addRole(Role role) {
        return roleRepo.save(role);
    }

    @Transactional
    public void addRoleToUser(Long userId, Long roleId) {
        Role role = roleRepo.getRoleById(roleId)
                .orElseThrow( () -> new EntityNotFoundException("Role by id " + roleId + " was not found"));
        User user = findUserById(userId);
        user.getRoles().add(role);
    }

    @Transactional
    public void addRoleToUser(String username, String name) {
        Role role = roleRepo.getRoleByName(name)
                .orElseThrow( () -> new EntityNotFoundException("Role by name " + name + " was not found"));
        User user = findUserByUsername(username);
        user.getRoles().add(role);
    }

    public User findUserByUsername(String username) {
        return userRepo.findUserByUsername(username)
                .orElseThrow( () -> new EntityNotFoundException("Employee by username " + username + " was not found"));
    }

}
