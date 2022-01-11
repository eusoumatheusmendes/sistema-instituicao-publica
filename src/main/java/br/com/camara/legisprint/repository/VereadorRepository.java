package br.com.camara.legisprint.repository;

import br.com.camara.legisprint.model.Vereador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VereadorRepository extends JpaRepository<Vereador, Long> {

    @Query("SELECT  v FROM Vereador v WHERE v.camara.id = ?1")
    List<Vereador> buscarTodosOsVereadoresDaInstituicaoLogada(Long id);
}
