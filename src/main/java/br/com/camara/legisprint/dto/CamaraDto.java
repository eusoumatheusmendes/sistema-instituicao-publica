package br.com.camara.legisprint.dto;

import br.com.camara.legisprint.model.Camara;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class CamaraDto {

    @NotBlank(message = "Por favor, preencha o campo cnpj.")
    @Getter @Setter
    private String cnpj;

    @NotBlank(message = "Por favor, preencha o campo município.")
    @Getter @Setter
    private String municipio;

    @NotBlank(message = "Por favor, preencha o campo e-mail.")
    @Getter @Setter
    private String email;

    @NotBlank(message = "Por favor, preencha o campo senha.")
    @Getter @Setter
    private String senha;

    public Camara convertToCamara(){
        Camara camara = new Camara();

        camara.setCnpj(this.cnpj);
        camara.setMunicipio(this.municipio);
        camara.setEmail(this.email);
        camara.setSenha(this.senha);

        return camara;
    }
}
