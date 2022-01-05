package br.com.camara.legisprint.repository;

import br.com.camara.legisprint.model.CotaDeImpressao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface CotaParlamentarRepository extends JpaRepository<CotaDeImpressao, Long> {

    @Query("SELECT sum(c.quantidadeUtilizada) FROM CotaDeImpressao c WHERE c.data BETWEEN ?1 AND ?2")
    Integer retornarASomaDasImpressoesMensais(LocalDate dataInicio, LocalDate dataFim);

    @Query("SELECT sum(c.quantidadeUtilizada) FROM CotaDeImpressao  c WHERE c.data BETWEEN ?1 AND ?2")
    Integer retornarASomaDasImpressoesAnual(LocalDate dataInicio, LocalDate dataFim);
}
