package com.buscacode.admin.buscacodeadmin.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import com.buscacode.admin.buscacodeadmin.models.User;
import com.buscacode.admin.buscacodeadmin.models.dto.ParamDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/var")
public class PathVarialbleController {

  @Value("${config.username}")
  private String username;
  
  @Value("${config.listOfValue}")
  private String[] listOfValue;

  @Value("#{'${config.listOfValue}'.split('\\.')}") //Utilizando spring expression language SpEL
  private List<String> valueList;

  @Value("${config.code}")
  private Integer code;

  @Value("#{${config.valuesMap}}")
  private Map<String, Object> valuesMap;

  @Value("#{${config.valuesMap}.price.value}")
  private Double valuesMapPrice;
  
  @Value("#{${config.valuesMap}.price.currency}")
  private String valuesMapCurrency;

  @Autowired
  private Environment environment;


  @GetMapping("/bazz/{message}")
  public ParamDto baz(@PathVariable String message) {
    ParamDto param = new ParamDto();
    param.setMessage(message);
    return param;
  }

  @GetMapping("/mix/{product}/{id}")
  public Map<String, Object> mixPathVar(@PathVariable String product, @PathVariable Long id) {
    
    Map<String, Object> json = new HashMap<>();
    json.put("product", product);
    json.put("id", id);

    return json;
  }

  @PostMapping("/create")
  public User create(@RequestBody User user) {
      
      return user;
  }
  
  @GetMapping("/values")
  public Map<String, Object> values(@Value("${config.message}") String message) {
    Map<String, Object> json = new HashMap<>();
    json.put("username", username);
    json.put("message", message);
    json.put("listOfValue", listOfValue);
    json.put("valueList", valueList);
    json.put("code", code);
    json.put("valuesMap", valuesMap);
    json.put("valuesMapPrice", valuesMapPrice);
    json.put("valuesMapCurrency", valuesMapCurrency);
    json.put("environment.valuesMap2", environment.getProperty("config.valuesMap2"));
    return json;
  }
  
}
