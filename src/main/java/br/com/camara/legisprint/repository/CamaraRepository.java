package br.com.camara.legisprint.repository;

import br.com.camara.legisprint.model.Camara;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CamaraRepository extends JpaRepository<Camara, Long> {

    @Query("SELECT c FROM Camara c WHERE c.id = ?1")
    Camara buscarPorId(Long id);

    Camara findByEmail(String email);
}
