package br.com.camara.legisprint.controller;

import br.com.camara.legisprint.dto.CamaraDto;
import br.com.camara.legisprint.model.Camara;
import br.com.camara.legisprint.repository.CamaraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String abrirPaginaDecadastro(CamaraDto camaraDto){
        return "/camara/cadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid CamaraDto camaraDto, BindingResult result, RedirectAttributes ra){
        if(result.hasErrors()){
            return "/camara/cadastro";
        }
        Camara camara = camaraDto.convertToCamara();
        repository.save(camara);
        ra.addFlashAttribute("sucesso", "Cadastro realizado com sucesso!");
        return "redirect:/acesso/login";
    }
}
