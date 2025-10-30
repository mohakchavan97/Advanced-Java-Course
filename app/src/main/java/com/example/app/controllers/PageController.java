/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Mohak Chavan
 */
@Controller
public class PageController {

    @GetMapping("/")
    public String hello() {
        return "index";
    }
    
    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
