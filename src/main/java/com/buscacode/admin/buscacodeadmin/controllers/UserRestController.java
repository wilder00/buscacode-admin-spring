package com.buscacode.admin.buscacodeadmin.controllers;

// import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buscacode.admin.buscacodeadmin.models.User;
import com.buscacode.admin.buscacodeadmin.models.dto.UserDto;

@RestController
@RequestMapping("/api")
public class UserRestController {

  @GetMapping("/details-map")
  public Map<String, Object> detailsMap() {
    User user = new User("Wilder", "Trujillo");
    Map<String, Object> body = new HashMap<>();
    body.put("title", "Hola Mundo desde spring");
    body.put("user", user);
     
    return body;
  }

  @GetMapping("/details")
  public UserDto details() {
    UserDto userDto = new UserDto();

    User user = new User("Wilder", "Trujillo");

    userDto.setUser(user);
    userDto.setTitle("Hola Mundo desde dto");
     
    return userDto;
  }
  
  @GetMapping("/list")
  public List<User> list() {
    User user = new User("Andres", "Guzman");
    User user2 = new User("Pepe", "Doe");
    User user3 = new User("Jhon", "Quispe");

    List<User> users = Arrays.asList(user, user2, user3);
    // List<User> users = new ArrayList<>();
    // users.add(user);
    // users.add(user1);
    // users.add(user2);
    // users.add(user3);
     
    return users;
  }

}
