package br.com.camara.legisprint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Camara extends AbstractEntity<Long>{

    @Setter   @Getter
    @Column
    @NotBlank(message = "Por favor, informe um CNPJ.")
    private String cnpj;

    @Setter   @Getter
    @Column
    @NotBlank(message = "Por favor, informe no nome do município.")
    private String municipio;

    @Setter   @Getter
    @Column
    @NotBlank(message = "Por favor, informe um e-mail.")
    private String email;

    @Setter   @Getter
    @Column
    @NotBlank(message = "Por favor, informe uma senha.")
    private String senha;

    @Setter   @Getter
    @Column
    @NotNull(message = "Por favor, informe a quantidade de cota de impressão/cópia dos parlamentares.")
    private Integer quantidadeLimiteDeCotaMensal;

}
