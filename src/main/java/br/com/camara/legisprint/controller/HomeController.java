package br.com.camara.legisprint.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping
    public String abrirPaginaInicial(){
        return "/home";
    }

    @GetMapping("/login")
    public String abrirPaginaDeLogin(){
        return "/acesso/login";
    }

    @GetMapping("/login/error")
    public String errorLogin(ModelMap model){
        model.addAttribute("alerta", "Usuário ou senha inválidos. Por favor, tente novamente.");
        return "/acesso/login";
    }

}
