package com.lyf.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author lyf
 * @Date 2018/11/7 0007 12:09
 */
@Controller
@RequestMapping("index")
public class IndexController {
  
  @Value("${spring.name}")
  private String name;

  @RequestMapping("test01")
  public String test01(Model model){
    model.addAttribute("name",name);
    return "index";
  }
  
}
