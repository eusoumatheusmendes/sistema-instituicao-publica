package br.com.camara.legisprint.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Vereador extends AbstractEntity<Long>{

    @Column
    @Getter @Setter
    private String nome;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "id_partido_fk")
    private Partido partido;

    @Column
    @Getter @Setter
    private String nomeParlamentar;

    @Column
    @Getter @Setter
    private LocalDate dataInicioLegislatura;

    @Column
    @Getter @Setter
    private LocalDate dataFimLegislatura;

    @Column
    @Enumerated(value = EnumType.STRING)
    @Getter @Setter
    private Status status;

    @Getter @Setter
    @OneToOne
    @JoinColumn(name = "id_copia_fk")
    private CotaCopia cotaCopia;
}
