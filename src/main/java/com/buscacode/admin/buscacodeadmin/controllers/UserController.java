package com.buscacode.admin.buscacodeadmin.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.buscacode.admin.buscacodeadmin.models.User;

@Controller
public class UserController {

  @GetMapping("/details")
  public String details(Model model) {
    User user = new User("Wilder", "Trujillo");

    model.addAttribute("title", "Hola Mundo desde spring");
    model.addAttribute("user", user);
    
    return "details";
  }
  
  @GetMapping("/list")
  public String list(ModelMap model) {
    List<User> users = Arrays.asList(
      new User("Pepa", "Gonzales", "pepa.gonzales@gim.com"),
      new User("Sandra", "Rivera", "sandra.rivera@correo.com"),
      new User("Mario", "Bros", "mario.bros@correo.com"),
      new User("Juan", "Libra")
    );
    
    model.addAttribute("title", "Madelmap list");
    model.addAttribute("users", users);
    
    return "list";
  }

}
