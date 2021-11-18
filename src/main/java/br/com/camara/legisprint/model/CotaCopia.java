package br.com.camara.legisprint.model;

import br.com.camara.legisprint.services.Limitavel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CotaCopia extends AbstractEntity<Long> implements Limitavel {

    @Getter @Setter
    @Column
    private String nome;

    @Getter @Setter
    @Column
    private Integer quantidade;

    @Override
    public Integer getLimite() {
        return getQuantidade();
    }
}
