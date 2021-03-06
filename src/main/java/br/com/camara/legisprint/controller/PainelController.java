package br.com.camara.legisprint.controller;

import br.com.camara.legisprint.model.Vereador;
import br.com.camara.legisprint.repository.CamaraRepository;
import br.com.camara.legisprint.repository.VereadorRepository;
import br.com.camara.legisprint.services.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/painel")
@Controller
public class PainelController {

    @Autowired
    private VereadorRepository vereadorRepository;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private CotaController cotaController;

    @Autowired
    private CamaraRepository camaraRepository;

    @GetMapping("/dashboard")
    public String abrirPainelPrincipal(Model model, Vereador vereador){
        model.addAttribute("somaDoUsoDeCotaMensal", cotaController.quantidadeUsadaDoMes());
        model.addAttribute("somaDoUsoDeCotaAnual", cotaController.quantidadeDeCotaUsadaNoAno());
        model.addAttribute("vereadores", vereadorRepository.buscarTodosOsVereadoresDaInstituicaoLogada(
                autenticacaoService.retornarIdDaInstituicaoLogada()));
        model.addAttribute("idDaInstituicaoLogada", autenticacaoService.retornarIdDaInstituicaoLogada().longValue());
        model.addAttribute("nomeDoMunicipioDaInstituicaoLogada", camaraRepository.buscarNomeDoMunicipioDaInstituicaoLogada(autenticacaoService.retornarEmailDaInstituicaoLogada()));
        return "/painel/principal";
    }

    @GetMapping("/area")
    public String areaDoLogado(Model model){
        model.addAttribute("nomeDoMunicipioDaInstituicaoLogada", camaraRepository.buscarNomeDoMunicipioDaInstituicaoLogada(autenticacaoService.retornarEmailDaInstituicaoLogada()));
        model.addAttribute("somaDoUsoDeCotaMensal", cotaController.quantidadeUsadaDoMes());
        model.addAttribute("somaDoUsoDeCotaAnual", cotaController.quantidadeDeCotaUsadaNoAno());
        return "/camara/area";
    }

}
