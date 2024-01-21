package com.buscacode.admin.buscacodeadmin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping({"", "/", "home"})
  public String home(){

    return "redirect:/list"; // cambia la url y manda a volver a hacer la request
  }
  
  @GetMapping({"/home2"})
  public String home2(){

    return "forward:/api/params/foo"; // mantiene la url y el request solo cambia el controlador
  }

  

}
