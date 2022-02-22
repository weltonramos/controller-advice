package br.com.contabilizei.controlleradvice.exceptionhandler;

import br.com.contabilizei.controlleradvice.exception.CepNuloOuEmBrancoException;
import br.com.contabilizei.controlleradvice.exception.EnderecoExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class EnderecoHandlerController {

    @ExceptionHandler(CepNuloOuEmBrancoException.class)
    public ResponseEntity<EnderecoExceptionHandler> cepNuloOuEmBrancoException(CepNuloOuEmBrancoException e) {
        EnderecoExceptionHandler enderecoExceptionHandler = EnderecoExceptionHandler.builder()
                .titulo("CepNuloOuEmBrancoException")
                .status(HttpStatus.BAD_REQUEST)
                .detalhes("Cep não foi informado")
                .timeStamp(new Date().getTime())
                .mensagem("O CEp não pode ser nulo ou estar em branco")
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(enderecoExceptionHandler);
    }
}
