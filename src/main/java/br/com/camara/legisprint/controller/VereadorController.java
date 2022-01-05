package br.com.camara.legisprint.controller;

import br.com.camara.legisprint.model.Camara;
import br.com.camara.legisprint.model.Partido;
import br.com.camara.legisprint.model.Vereador;
import br.com.camara.legisprint.repository.CamaraRepository;
import br.com.camara.legisprint.repository.PartidoRepository;
import br.com.camara.legisprint.repository.VereadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@Controller
@RequestMapping("/vereador")
public class VereadorController {

    @Autowired
    private VereadorRepository repository;

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private CamaraRepository camaraRepository;

    @GetMapping("/cadastro")
    public String abrirPaginaDeCadastro(Vereador vereador){
        return "/vereador/cadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid Vereador vereador, BindingResult result, RedirectAttributes ra){
        if(result.hasErrors()){
            return "/vereador/cadastro";
        }
        repository.save(vereador);
        ra.addFlashAttribute("sucesso", "Parlamentar cadastrado com sucesso!");
        return "redirect:/painel";
    }

    @ModelAttribute("partidos")
    public Collection<Partido> populaCombobox(){
        return partidoRepository.findAll();
    }


}
