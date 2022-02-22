package br.com.contabilizei.controlleradvice.exception;

public class RegiaoNaoAtendidaException extends RuntimeException {

    public RegiaoNaoAtendidaException(String mensagem) {
        super(mensagem);
    }
}
