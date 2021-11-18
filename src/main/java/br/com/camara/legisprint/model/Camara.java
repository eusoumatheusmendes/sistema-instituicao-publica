package br.com.camara.legisprint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Camara extends AbstractEntity<Long>{

    @Getter @Setter
    private String cnpj;

    @Getter @Setter
    private String municipio;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String senha;

}
