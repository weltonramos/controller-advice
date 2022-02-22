package br.com.contabilizei.controlleradvice.handler;

import br.com.contabilizei.controlleradvice.exception.EnderecoExceptionHandler;
import br.com.contabilizei.controlleradvice.exception.RegiaoNaoAtendidaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class EnderecoHandler {

    @ExceptionHandler(RegiaoNaoAtendidaException.class)
    public ResponseEntity<EnderecoExceptionHandler> regiaoNaoAtendidaException(RegiaoNaoAtendidaException e) {
        EnderecoExceptionHandler enderecoExceptionHandler = EnderecoExceptionHandler.builder()
                .titulo(e.getClass().getSimpleName())
                .status(HttpStatus.BAD_REQUEST)
                .detalhes("Cep pertence a região não atendida")
                .timeStamp(new Date().getTime())
                .mensagem(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(enderecoExceptionHandler);
    }
}
