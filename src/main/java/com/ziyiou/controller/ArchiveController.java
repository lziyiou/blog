package com.ziyiou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ziyiou
 */
@Controller
@RequestMapping("/archive")
public class ArchiveController {

   @GetMapping
    public String archive(){
       return "/archive-error";
   }
}
