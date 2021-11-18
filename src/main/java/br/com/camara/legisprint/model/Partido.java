package br.com.camara.legisprint.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
public class Partido extends AbstractEntity<Long> {

    @Column
    @Getter @Setter
    private String sigla;

    @Column
    @Getter @Setter
    private String nome;

}
