package com.buscacode.admin.buscacodeadmin.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buscacode.admin.buscacodeadmin.models.dto.ParamDto;
import com.buscacode.admin.buscacodeadmin.models.dto.ParamMixDto;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/params")
public class RequestParamsController {

  @GetMapping("/foo")
  public ParamDto foo(@RequestParam(required=false, defaultValue = "default value", name="message") String message) {
    ParamDto param = new ParamDto();
    param.setMessage(message);
    return param;
  }

  @GetMapping("/bar")
  public ParamDto bar(@RequestParam String text, @RequestParam Integer code) {
    ParamDto param = new ParamDto();
    param.setMessage(text);
    param.setCode(code);
    return param;
  }
  
  @GetMapping("/request")
  public ParamMixDto request(HttpServletRequest request) {
    
    Integer code = 0;
    try {
      code = Integer.parseInt(request.getParameter("code"));
    } catch (NumberFormatException e) {
    }

    ParamMixDto params = new ParamMixDto();
    params.setCode(code);
    params.setMessage(request.getParameter("message"));  
    
    return params;
  }
  
}
