package br.com.contabilizei.controlleradvice.service;

import br.com.contabilizei.controlleradvice.exception.CepInvalidoException;
import br.com.contabilizei.controlleradvice.exception.CepNaoEncontradoException;
import br.com.contabilizei.controlleradvice.exception.CepNuloOuEmBrancoException;
import br.com.contabilizei.controlleradvice.exception.RegiaoNaoAtendidaException;
import br.com.contabilizei.controlleradvice.model.Endereco;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EnderecoService {

    private static final Pattern pattern = Pattern.compile("\\d{5}-?\\d{3}");

    public Endereco consultarEndereco(String cep) {

        if (Strings.isBlank(cep)) {
            throw new CepNuloOuEmBrancoException("Informe um CEP");
        }

        validarCep(cep);
        String cepFormatado = cep.replace("-", "");
        Endereco endereco = realizarConsultaViaCep(cepFormatado);

        if (endereco == null || endereco.getUf() == null || endereco.getUf().equals("PR")) {
            throw new RegiaoNaoAtendidaException("A região do CEP " + cepFormatado + " não é atendida por nossos serviços.");
        }
        return endereco;
    }

    private void validarCep(String cep) {
        Matcher matcher = pattern.matcher(cep);

        if (!matcher.matches()) {
            throw new CepInvalidoException("O CEP " + cep + " é inválido.");
        }
    }

    private Endereco realizarConsultaViaCep(String cepFormatado) {
        RestTemplate restTemplate = new RestTemplate();

        final String URL = "https://viacep.com.br/ws/{cep}/json/";

        try {
            ResponseEntity<Endereco> retorno = restTemplate.getForEntity(URL, Endereco.class, cepFormatado);

            if (retorno.getStatusCode() != HttpStatus.OK) {
                throw new CepNaoEncontradoException("CEP " + cepFormatado + " não foi encontrado.");
            }

            return retorno.getBody();
        } catch (RestClientException e) {
            System.out.println("Finja que isso é um log: " + e.getMessage());
            throw e;
        }
    }
}
