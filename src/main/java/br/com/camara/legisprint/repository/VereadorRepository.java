package br.com.camara.legisprint.repository;

import br.com.camara.legisprint.model.Vereador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VereadorRepository extends JpaRepository<Vereador, Long> {
}
