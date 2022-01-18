package br.com.camara.legisprint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Camara extends AbstractEntity<Long>{

    @Setter   @Getter
    @Column
    @NotBlank(message = "Por favor, informe um CNPJ.")
    @Size(max = 14, message = "CNPJ não pode conter mais que 14 caracteres.")
    private String cnpj;

    @Setter   @Getter
    @Column
    @NotBlank(message = "Por favor, informe o nome do município.")
    @Size(max = 50, message = "Nome do município não pode conter mais que 100 caracteres.")
    private String municipio;

    @Setter   @Getter
    @Column
    @NotBlank(message = "Por favor, informe um e-mail.")
    @Size(max = 50, message = "E-mail não pode conter mais que 100 caracteres.")
    @Email(message = "Por favor, informe um e-mail válido.")
    private String email;

    @Setter   @Getter
    @Column
    @NotBlank(message = "Por favor, informe uma senha.")
    @Size(min = 5, message = "Senha não pode conter menos que 5 caracteres.")
    private String senha;

    @Setter   @Getter
    @Column
    @NotNull(message = "Por favor, informe a quantidade de cota de impressão/cópia dos parlamentares.")
    private Integer quantidadeLimiteDeCotaMensal;

}
