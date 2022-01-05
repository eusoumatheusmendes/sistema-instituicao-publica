package br.com.camara.legisprint.controller;

import br.com.camara.legisprint.model.Camara;
import br.com.camara.legisprint.repository.CamaraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/camara")
public class CamaraController {

    @Autowired
    private CamaraRepository repository;

    @GetMapping("/cadastro")
    public String abrirPaginaDecadastro(Camara camara){
        return "/camara/cadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid Camara camara, BindingResult result, RedirectAttributes ra, Model model){
        if(result.hasErrors()){
            return "/camara/cadastro";
        }
        BCryptPasswordEncoder senhaCriptografada = new BCryptPasswordEncoder();
        camara.setSenha(senhaCriptografada.encode(camara.getSenha()));
        repository.save(camara);
        model.addAttribute("sucesso", "Cadastro realizado com sucesso!");
        //ra.addFlashAttribute("sucesso", "Cadastro realizado com sucesso!");
        return "/acesso/login";
    }
}
