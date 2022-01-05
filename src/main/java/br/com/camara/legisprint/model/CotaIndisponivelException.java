package br.com.camara.legisprint.model;

public class CotaIndisponivelException extends RuntimeException {
    public CotaIndisponivelException(String mensagem) {
        super(mensagem);
    }
}
