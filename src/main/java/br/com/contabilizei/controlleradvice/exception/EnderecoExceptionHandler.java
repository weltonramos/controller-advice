package br.com.contabilizei.controlleradvice.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@Setter
public class EnderecoExceptionHandler {

    private String titulo;
    private HttpStatus status;
    private String detalhes;
    private long timeStamp;
    private String mensagem;
}
