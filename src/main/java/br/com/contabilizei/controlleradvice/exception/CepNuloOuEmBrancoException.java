package br.com.contabilizei.controlleradvice.exception;

public class CepNuloOuEmBrancoException extends RuntimeException {

    public CepNuloOuEmBrancoException(String mensagem) {
        super(mensagem);
    }
}
