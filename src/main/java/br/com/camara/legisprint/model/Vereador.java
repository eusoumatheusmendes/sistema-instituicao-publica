package br.com.camara.legisprint.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Vereador extends AbstractEntity<Long>{

    public Vereador(){
        this.setEmExercicio(true);
    }

    @Column @Getter @Setter
    @NotBlank(message = "Por favor, informe o nome completo do vereador.")
    @Size(max = 50, message = "Nome completo não pode conter mais que 50 caracteres.")
    private String nomeCompleto;

    @Column @Getter @Setter
    @NotBlank(message = "Por favor, informe o nome parlamentar usado na campanha.")
    @Size(max = 50, message = "Nome parlamentar não pode conter mais que 100 caracteres.")
    private String nomeParlamentar;

    @Column @Getter @Setter
    @NotNull(message = "Por favor, informe a data de início da legislatura.")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataInicioLegislatura;

    @Column @Getter @Setter
    @NotNull(message = "Por favor, informe a data de aniversário do vereador.")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataDeAniversario;

    @Column @Getter @Setter
    @NotBlank(message = "Por favor, informe um número de contato do vereador.")
    @Size(max = 11, message = "Número de celular não pode conter mais que 11 caracteres.")
    private String numeroDeCelular;

    @Column @Getter @Setter
    private Boolean emExercicio;

    @Column @Getter @Setter
    private Integer quantidadeDisponivelDeCotaDeImpressao;

    @ManyToOne @Getter @Setter
    @JoinColumn(name = "id_camara_fk")
    private Camara camara;

    @ManyToOne @Getter @Setter
    @JoinColumn(name = "id_partido_fk")
    @Valid
    @NotNull(message = "Selecione o partido político")
    private Partido partido;


    public LocalDate retornarDataFinalDaLegislatura(){
        return this.dataInicioLegislatura.plusMonths(47L);
    }

    public Double getPercentualUtilizadoDeCotaDeImpressao(Integer quantidadePermitidaParaUso){
       Double percentual = new Double(0.0);
       Integer quantidadeUsada = quantidadePermitidaParaUso - this.quantidadeDisponivelDeCotaDeImpressao;
       percentual = Double.valueOf(quantidadeUsada/quantidadePermitidaParaUso);
        return percentual;
    }
    
    public void fazerUsoDaCotaDeImpressao(Integer quantidadeUsada){
        LocalDate hoje = LocalDate.now();
        verificarDisponibilidadeDeCota(quantidadeUsada);

        if(ehPrimeiroDiaDoMes(hoje)){
            setQuantidadeDisponivelDeCotaDeImpressao(camara.getQuantidadeLimiteDeCotaMensal());
            setQuantidadeDisponivelDeCotaDeImpressao(this.getQuantidadeDisponivelDeCotaDeImpressao() - quantidadeUsada);
        }
        setQuantidadeDisponivelDeCotaDeImpressao(this.getQuantidadeDisponivelDeCotaDeImpressao() - quantidadeUsada);
    }
    
    public boolean ehPrimeiroDiaDoMes(LocalDate hoje){
        return hoje.getDayOfMonth() == 1;
    }

    public void verificarDisponibilidadeDeCota(Integer quantidadeSolicitada){
        if(getQuantidadeDisponivelDeCotaDeImpressao() < quantidadeSolicitada){
            throw new CotaIndisponivelException("Vereador " + this.getNomeParlamentar() + " não possui cota suficiente para este mês." + " Quantidade disponível: "
                +getQuantidadeDisponivelDeCotaDeImpressao());
        }
    }

    public boolean ehAniversarianteDoDia(){
        LocalDate hoje = LocalDate.now();
        int dia = hoje.getDayOfMonth();
        int mes = hoje.getMonth().getValue();
        return this.getDataDeAniversario().getMonth().getValue() == mes &&
                this.getDataDeAniversario().getDayOfMonth() == dia;
    }

    public boolean ehCadastroNovo(){
        return this.getId() == null;
    }

}
