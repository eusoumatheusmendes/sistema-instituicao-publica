package br.com.camara.legisprint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class CotaDeImpressao extends AbstractEntity<Long> {

    public CotaDeImpressao(){
        this.setData(LocalDate.now());
    }

    @Getter @Setter
    @Column
    @NotBlank(message = "Por favor, informe uma descrição. Ex: pessoa que faz uso da cota.")
    @Size(max = 50, message = "Descrição de uso não pode conter mais que 50 caracteres.")
    private String descricao;

    @Getter @Setter
    @Column
    @NotNull(message = "Por favor, informe a quantidade de impressão/cópia usada.")
    @Min(value = 1, message = "Você deve estar utilizando pelo menos 1 impressão/cópia.")
    private Integer quantidadeUtilizada;

    @JoinColumn(name = "id_vereador_fk")
    @OneToOne
    @Getter @Setter
    private Vereador vereador;

    @Getter @Setter
    @Column
    private LocalDate data;

}
