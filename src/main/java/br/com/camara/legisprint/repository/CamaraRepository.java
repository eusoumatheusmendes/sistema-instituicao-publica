package br.com.camara.legisprint.repository;

import br.com.camara.legisprint.model.Camara;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CamaraRepository extends JpaRepository<Camara, Long> {
}
