package br.com.camara.legisprint.controller;

import br.com.camara.legisprint.model.Partido;
import br.com.camara.legisprint.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/partido")
public class PartidoController{

    @Autowired
    private PartidoRepository repository;

    @GetMapping("/cadastro")
    public String abrirPaginaDeCadastro(Partido partido){
        return "/partido/cadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid Partido partido, BindingResult result, RedirectAttributes ra){
        if(result.hasErrors()){
            return "/partido/cadastro";
        }
        repository.save(partido);
        ra.addFlashAttribute("sucesso", "Partido político cadastrado com sucesso!");
        return "redirect:/vereador/cadastro";
    }
}
