package com.secorg.spring.samlreact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Index controller handles root of the application.
 */
@Controller
public class IndexController {

  /**
   * This just returns the index template that will either display the login redirect page or landing page.
   * @return index template.
   */
  @RequestMapping(value = "/")
  public String index(){
    return "index.html";
  }
}
