package br.com.camara.legisprint.controller;

import br.com.camara.legisprint.repository.CamaraRepository;
import br.com.camara.legisprint.services.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private CamaraRepository camaraRepository;

    @GetMapping
    public String abrirPaginaInicial(Model model){
        model.addAttribute("emailDaInstituicao", autenticacaoService.retornarEmailDaInstituicaoLogada());
        model.addAttribute("nomeDoMunicipioDaInstituicaoLogada", camaraRepository.
                buscarNomeDoMunicipioDaInstituicaoLogada(autenticacaoService.retornarEmailDaInstituicaoLogada()));
        informarSeAInstituicaoEstaLogada(model);
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

    public void informarSeAInstituicaoEstaLogada(Model model){
        model.addAttribute("estahLogada", autenticacaoService.estahLogada());
    }
}
