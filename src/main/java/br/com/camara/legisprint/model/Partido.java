package br.com.camara.legisprint.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Partido extends AbstractEntity<Long> {

    @Column  @Getter @Setter
    @NotBlank(message = "Por favor, informe a sigla do partido.")
    @Size(max = 20, message = "Sigla não pode conter mais que 20 caracteres.")
    private String sigla;

    @Column @Getter @Setter
    @NotBlank(message = "Por favor, informe o nome do partido.")
    @Size(max = 50, message = "Nome do partido não pode conter mais que 100 caracteres.")
    private String nome;

}
