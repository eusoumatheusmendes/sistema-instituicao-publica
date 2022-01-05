package br.com.camara.legisprint.converter;

import br.com.camara.legisprint.model.Partido;
import br.com.camara.legisprint.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StringPartidoConverter implements Converter<String, Optional<Partido>> {

    @Autowired
    private PartidoRepository partidoRepository;

    @Override
    public Optional<Partido> convert(String id) {
        Optional<Partido> partido =  partidoRepository.findById(Long.valueOf(id));
        return partido;
    }
}
