package br.com.camara.legisprint.controller;

import br.com.camara.legisprint.model.Camara;
import br.com.camara.legisprint.model.Partido;
import br.com.camara.legisprint.model.Vereador;
import br.com.camara.legisprint.repository.CamaraRepository;
import br.com.camara.legisprint.repository.PartidoRepository;
import br.com.camara.legisprint.repository.VereadorRepository;
import br.com.camara.legisprint.services.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private AutenticacaoService autenticacaoService;

    @GetMapping("/cadastro")
    public String abrirPaginaDeCadastro(Vereador vereador){
        return "/vereador/cadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid Vereador vereador, BindingResult result, RedirectAttributes ra){
        if(result.hasErrors()){
            return "/vereador/cadastro";
        }
        vincularParlamentarACamaraLogada(vereador);
        autenticacaoService.vincularCotaDaInstituicaoLogadaAoParlamentar(vereador);
        repository.save(vereador);
        ra.addFlashAttribute("sucesso", "Parlamentar cadastrado com sucesso!");
        return "redirect:/painel";
    }

    @ModelAttribute("partidos")
    public Collection<Partido> populaCombobox(){
        return partidoRepository.findAll();
    }

    @GetMapping("/lista")
    public String listar(Model model){
        Camara camara = autenticacaoService.trazerInstituicaoLogada();
        model.addAttribute("vereadores", repository.buscarTodosOsVereadoresDaInstituicaoLogada(camara.getId()));
        return "/vereador/lista";

    }

    public void vincularParlamentarACamaraLogada(Vereador vereador){
        Camara camaraLogada = camaraRepository.buscarPorId(autenticacaoService.retornarIdDaInstituicaoLogada());
        vereador.setCamara(camaraLogada);
    }


}
