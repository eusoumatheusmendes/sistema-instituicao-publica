package br.com.camara.legisprint.services;

import br.com.camara.legisprint.model.Camara;
import br.com.camara.legisprint.model.Vereador;
import br.com.camara.legisprint.repository.CamaraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class AutenticacaoService implements UserDetailsService{

    @Autowired
    private CamaraRepository repository;

    public Camara buscarPorEmail(String email){
        return repository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Camara camara = repository.findByEmail(email);
        return new User(camara.getEmail(), camara.getSenha(), new HashSet<>());
    }

    public String retornarEmailDaInstituicaoLogada(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public Long retornarIdDaInstituicaoLogada(){
        return repository.buscarIdDaInstituicaoLogadaPorEmail(retornarEmailDaInstituicaoLogada());
    }

    public void vincularCotaDaInstituicaoLogadaAoParlamentar(Vereador vereador){
        Camara camaraLogada = repository.buscarPorId(retornarIdDaInstituicaoLogada());
        vereador.setQuantidadeDisponivelDeCotaDeImpressao(camaraLogada.getQuantidadeLimiteDeCotaMensal());
    }

    public Camara trazerInstituicaoLogada(){
        return repository.buscarPorId(retornarIdDaInstituicaoLogada());
    }
}
