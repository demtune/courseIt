package com.example.demoit2.service.impl;

import com.example.demoit2.dto.RegistrationUserDto;
import com.example.demoit2.entity.users.RoleEntity;
import com.example.demoit2.entity.users.UserEntity;
import com.example.demoit2.exception.UserAlreadyExistException;
import com.example.demoit2.repository.RoleRepository;
import com.example.demoit2.repository.UserRepository;
import com.example.demoit2.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("User not found in database");
            throw new UsernameNotFoundException("User not found in database");
        } else {
            log.info("User found in database:{}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        log.info("Saving new user:{} to database", user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public RoleEntity saveRole(RoleEntity role) {
        log.info("Saving new role:{} to database", role.getRoleName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Add role:{} to user:{}", roleName, username);
        UserEntity user = userRepository.findByUsername(username);
        RoleEntity role = roleRepository.findByRoleName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public UserEntity getUser(String username) {
        UserEntity user = userRepository.findByUsername(username);
        log.info("Getting user:{}", username);
        return user;
    }

    @Override
    public List<UserEntity> getUsers() {
        log.info("Getting all users");
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        log.info("User:{} is deleted", userRepository.findById(id).get().getUsername());
        userRepository.deleteById(id);
    }

    @Override
    public void deleteUsers(List<Long> ids) {
        log.info("Users deleted with ids:{}", ids);
        if (isNotEmpty(ids)) {
            userRepository.deleteAllByIdIn(ids);
        }
    }

    @Override
    public void blockUser(Long id) {
        log.info("User with id:{} is blocked", id);
        UserEntity user = userRepository.findById(id).get();
        user.setEnabled(false);
        userRepository.save(user);
    }

    @Override
    public void blockUsers(List<Long> ids) {
        log.info("Users blocked with ids:{}", ids);
        if (isNotEmpty(ids)) {
            ids.forEach(id -> {
                UserEntity user = userRepository.findById(id).get();
                user.setEnabled(false);
                userRepository.save(user);
            });
        }
    }

    @Override
    public void unblockUser(Long id) {
        log.info("User with id:{} is unblocked", id);
        UserEntity user = userRepository.findById(id).get();
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public void unblockUsers(List<Long> ids) {
        log.info("Users unblocked with ids:{}", ids);
        if (isNotEmpty(ids)) {
            ids.forEach(id -> {
                UserEntity user = userRepository.findById(id).get();
                user.setEnabled(true);
                userRepository.save(user);
            });
        }
    }

    @Override
    public Optional<UserEntity> registration(RegistrationUserDto userDto) throws UserAlreadyExistException {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        if (userRepository.findByUsername(userDto.getUsername()) != null) {
            throw new UserAlreadyExistException("User with that name already exists");
        }
        return Optional.ofNullable(userDto)
                .map(u -> {
                    final UserEntity user = new UserEntity(
                            u.getUsername(),
                            u.getPassword(),
                            u.getEmail()
                    );
                    user.setPassword(encoder.encode(userDto.getPassword()));
                    user.setDateRegistration(LocalDateTime.now());
                    user.setLastLogin(LocalDateTime.now());
                    userRepository.save(user);
                    addRoleToUser(user.getUsername(), "ROLE_USER");
                    return user;
                });
    }
}
