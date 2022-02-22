package br.com.contabilizei.controlleradvice.controller;

import br.com.contabilizei.controlleradvice.model.Endereco;
import br.com.contabilizei.controlleradvice.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/{cep}")
    public ResponseEntity<Endereco> consultarEndereco(@PathVariable("cep") String cep) {
        return ResponseEntity.ok(enderecoService.consultarEndereco(cep));
    }
}

