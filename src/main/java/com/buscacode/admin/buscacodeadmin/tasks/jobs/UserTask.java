package com.buscacode.admin.buscacodeadmin.tasks.jobs;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.buscacode.admin.buscacodeadmin.entities.User;
import com.buscacode.admin.buscacodeadmin.services.UserService;

@Component
public class UserTask {

  @Autowired
  private UserService userService;

  @Value("${cronjob.seed.password:}")
  private String defaultPasswordSeed;

  public void createInitialUsers() {
    Optional<User> optionalUser = userService.findByUsername("wilder00");

    if (!optionalUser.isPresent()) {
      User user = new User();
      user.setName("Wilder");
      user.setLastname("Trujillo");
      user.setEmail("nadie437@gmail.com");
      user.setUsername("wilder00");
      user.setPassword(defaultPasswordSeed);
      user.setIsAdmin(true);
      userService.save(user);
    }
  }
}
