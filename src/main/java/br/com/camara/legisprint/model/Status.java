package br.com.camara.legisprint.model;

public enum Status {

    EM_EXERCICIO ("Em exercício"),
    AFASTADO("Afastado");

    private String situacao;

    Status(String situacao) {
        this.situacao = situacao;
    }

    public String getSituacao() {
        return situacao;
    }
}
