package br.com.camara.legisprint.repository;

import br.com.camara.legisprint.model.Camara;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CamaraRepository extends JpaRepository<Camara, Long> {

    @Query("SELECT c FROM Camara c WHERE c.id = ?1")
    Camara buscarPorId(Long id);

    @Query("SELECT c.id FROM Camara c WHERE c.email = ?1")
    Long buscarIdDaInstituicaoLogadaPorEmail(String email);

    Camara findByEmail(String email);

    @Query("SELECT c.municipio FROM Camara c WHERE c.email = ?1")
    String buscarNomeDoMunicipioDaInstituicaoLogada(String email);

}
