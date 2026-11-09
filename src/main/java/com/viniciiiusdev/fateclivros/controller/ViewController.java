package com.viniciiiusdev.fateclivros.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/pagina-inicial")
    public String paginaInicial(){
        return "redirect:/pagina-inicial.html";
    }
}
