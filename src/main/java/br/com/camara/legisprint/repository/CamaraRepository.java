package br.com.camara.legisprint.repository;

import br.com.camara.legisprint.model.Camara;
import br.com.camara.legisprint.model.Vereador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CamaraRepository extends JpaRepository<Camara, Long> {

    @Query("SELECT c FROM Camara c WHERE c.id = ?1")
    Camara buscarPorId(Long id);

    @Query("SELECT c.id FROM Camara c WHERE c.email = ?1")
    Long buscarIdDaInstituicaoLogadaPorEmail(String email);

    Camara findByEmail(String email);


}
