package br.com.contabilizei.controlleradvice.exception;

public class CepNaoEncontradoException extends RuntimeException {

    public CepNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
