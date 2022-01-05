package br.com.camara.legisprint.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Partido extends AbstractEntity<Long> {

    @Column  @Getter @Setter
    @NotBlank(message = "Por favor, informe a sigla do partido.")
    private String sigla;

    @Column @Getter @Setter
    @NotBlank(message = "Por favor, informe o nome do partido.")
    private String nome;

}
