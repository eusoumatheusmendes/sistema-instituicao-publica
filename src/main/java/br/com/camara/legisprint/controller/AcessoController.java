package br.com.camara.legisprint.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/acesso")
public class AcessoController {
    
    @GetMapping("/login")
    public String abrirPáginaDeLogin(){
        return "/acesso/login";
    }
}
