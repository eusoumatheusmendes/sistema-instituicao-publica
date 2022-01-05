package br.com.camara.legisprint.repository;

import br.com.camara.legisprint.model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidoRepository extends JpaRepository<Partido, Long> {
}
