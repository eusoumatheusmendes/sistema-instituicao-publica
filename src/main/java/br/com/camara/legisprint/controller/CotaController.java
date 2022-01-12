package br.com.camara.legisprint.controller;

import br.com.camara.legisprint.model.CotaDeImpressao;
import br.com.camara.legisprint.model.CotaIndisponivelException;
import br.com.camara.legisprint.model.Vereador;
import br.com.camara.legisprint.repository.CotaParlamentarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/cota")
public class CotaController {

    @Autowired
    private CotaParlamentarRepository repository;

    @GetMapping("/exibirCota/{id}")
    public String abrirFormularioParaUsoDeCota(@PathVariable("id") Vereador vereador, ModelMap model, CotaDeImpressao cota){
        model.addAttribute(vereador);
        retornarMesAtual();
        return "/cota-parlamentar/registro";
    }

    @PostMapping("/registrar/{id}")
    public String registrarUsoDeCotaParlamentar(@Valid CotaDeImpressao cotaDeImpressao, BindingResult result, @PathVariable("id") Vereador vereador, Model model, ModelMap modelMap, RedirectAttributes ra){
        cotaDeImpressao.setId(null);
        model.addAttribute(vereador);
        cotaDeImpressao.setVereador(vereador);
        retornarMesAtual();

        if(result.hasErrors()){
            return "/cota-parlamentar/registro";
        }
        try{
            vereador.fazerUsoDaCotaDeImpressao(cotaDeImpressao.getQuantidadeUtilizada());
        }catch(CotaIndisponivelException ex){
            modelMap.addAttribute("falha", ex.getMessage());
            return "/cota-parlamentar/registro";
        }
        repository.save(cotaDeImpressao);
        ra.addFlashAttribute("sucesso", "Uso de cota registrado com sucesso!");
        return "redirect:/painel/dashboard";
    }

    @GetMapping("/lista")
    public String getUsoDeCotas(Model model){
        model.addAttribute("registrosDeCotas", repository.findAll());
        return "/cota-parlamentar/lista";
    }

    @ModelAttribute("mes")
    public Integer retornarMesAtual(){
        return LocalDate.now().getMonth().getValue();
    }

    public Integer quantidadeUsadaDoMes(){
        LocalDate hoje = LocalDate.now();
        return repository.retornarASomaDasImpressoesMensais(LocalDate.now().withDayOfMonth(1), hoje.withDayOfMonth(30));
    }

    public Integer quantidadeDeCotaUsadaNoAno(){
        return repository.retornarASomaDasImpressoesAnual(LocalDate.now().withMonth(1), LocalDate.now().withMonth(12));
    }
}
