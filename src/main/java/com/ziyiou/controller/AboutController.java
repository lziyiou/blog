package com.ziyiou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ziyiou
 */
@Controller
@RequestMapping("/about")
public class AboutController {

   @GetMapping
    public String about(){
       return "/about";
   }
}
