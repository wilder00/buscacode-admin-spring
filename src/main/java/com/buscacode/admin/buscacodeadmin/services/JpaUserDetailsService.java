package com.buscacode.admin.buscacodeadmin.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buscacode.admin.buscacodeadmin.entities.User;
import com.buscacode.admin.buscacodeadmin.repositories.UserRepository;
import com.buscacode.admin.buscacodeadmin.security.model.UserDetail;

@Service
public class JpaUserDetailsService implements UserDetailsService{

  @Autowired
  private UserRepository repository;

  @Transactional(readOnly = true)
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // TODO Auto-generated method stub
    Optional<User> userOptional = repository.findByUsername(username);
    if(userOptional.isEmpty()) {
      throw new UsernameNotFoundException(String.format("username %s no existe en el sistema!", username) );
    }
    User user = userOptional.orElseThrow();

    List<GrantedAuthority> authorities = user.getRoles().stream()
    .map(role -> new SimpleGrantedAuthority(role.getName()))
    .collect(Collectors.toList());

    /* org.springframework.security.core.userdetails.User springUser = new org.springframework.security.core.userdetails.User(user.getUsername(),
      user.getPassword(),
      user.getIsEnabled(),
      true,
      true,
      true,
      authorities
    ); */

    UserDetail springUser = new UserDetail(user.getId(),user.getUsername(),
      user.getPassword(),
      user.getIsEnabled(),
      true,
      true,
      true,
      authorities
    );

    return springUser;
  }


}
