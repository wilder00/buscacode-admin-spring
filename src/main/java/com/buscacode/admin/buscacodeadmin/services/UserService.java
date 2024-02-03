package com.buscacode.admin.buscacodeadmin.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.buscacode.admin.buscacodeadmin.entities.Role;
import com.buscacode.admin.buscacodeadmin.entities.User;
import com.buscacode.admin.buscacodeadmin.repositories.RoleRepository;
import com.buscacode.admin.buscacodeadmin.repositories.UserRepository;
import com.buscacode.admin.buscacodeadmin.services.interfaces.Service;

@org.springframework.stereotype.Service
public class UserService implements Service<User> {
  private final String ROLE_ADMIN = "ROLE_ADMIN";
  private final String ROLE_USER = "ROLE_USER";
  

  @Autowired
  private UserRepository repository;

  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public List<User> findAll() {
    return StreamSupport
            .stream(repository.findAll().spliterator(), false)
            .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<User> findById(Long id) {
    return repository.findById(id);
  }
  
  @Transactional(readOnly = true)
  public Optional<User> findByUsername(String username) {
    return repository.findByUsername(username);
  }

  @Override
  @Transactional
  public User save(User user) {

    Optional<Role> optionaRoleUser = roleRepository.findByName(ROLE_USER);
    List<Role> roles = new ArrayList<>();
    
    //optionaRoleUser.ifPresent(role -> roles.add(role));
    optionaRoleUser.ifPresent(roles::add);

    if(user.getIsAdmin()) {
      Optional<Role> optionaRoleAdmin = roleRepository.findByName(ROLE_ADMIN);
      optionaRoleAdmin.ifPresent(role -> roles.add(role));
    }

    user.setRoles(roles);

    String passwordEncoded = passwordEncoder.encode( user.getPassword() );
    user.setPassword(passwordEncoded);
    return repository.save(user);
  }



  @Override
  public Optional<User> update(Long id, User recurso) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public Optional<User> delete(User recurso) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  public boolean existByUsername(String username){
    return repository.existsByUsername(username);
  }
}
