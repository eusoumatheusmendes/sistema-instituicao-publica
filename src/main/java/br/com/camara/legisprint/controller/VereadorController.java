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
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Vereador vereador, Model model){
        model.addAttribute(vereador);
        return "/vereador/cadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid Vereador vereador, BindingResult result, RedirectAttributes ra){
        if(result.hasErrors()){
            return "/vereador/cadastro";
        }
        vincularParlamentarACamaraLogada(vereador);
        if(vereador.ehCadastroNovo()){
            autenticacaoService.vincularCotaDaInstituicaoLogadaAoParlamentar(vereador);
        }
        repository.save(vereador);
        ra.addFlashAttribute("sucesso", "Ação executada com sucesso!!");
        return "redirect:/painel/dashboard";
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

    @GetMapping("/pesquisa-data")
    public String buscarUsoDeCotasPorData(){
        return "/vereador/tela-pesquisa";
    }

    @GetMapping("/confirma-exclusao/{id}")
    public String abrirTelaDeConfirmacaoDeExclusao(@PathVariable("id") Vereador vereador, Model model){
        model.addAttribute(vereador);
        return "/vereador/confirma-exclusao";
    }

    @GetMapping("/excluir/{id}")
    public String exluir(@PathVariable("id") Vereador vereador, RedirectAttributes ra){
        repository.delete(vereador);
        ra.addFlashAttribute("sucesso", "Parlamentar excluído(a) com sucesso!");
        return "redirect:/vereador/lista";
    }

}
