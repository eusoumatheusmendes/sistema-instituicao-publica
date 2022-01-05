package br.com.camara.legisprint.controller;

import br.com.camara.legisprint.model.Vereador;
import br.com.camara.legisprint.repository.VereadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/painel")
@Controller
public class PainelController {

    @Autowired
    private VereadorRepository vereadorRepository;

    @Autowired
    private CotaController cotaController;

    @GetMapping
    public String abrirPainelPrincipal(Model model, Vereador vereador){
        model.addAttribute("somaDoUsoDeCotaMensal", cotaController.quantidadeUsadaDoMes());
        model.addAttribute("somaDoUsoDeCotaAnual", cotaController.quantidadeDeCotaUsadaNoAno());
        model.addAttribute("vereadores", vereadorRepository.findAll());
        return "/painel/principal";
    }


}
