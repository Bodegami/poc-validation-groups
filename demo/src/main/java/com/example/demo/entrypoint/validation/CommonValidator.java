package com.example.demo.entrypoint.validation;

import com.example.demo.entrypoint.dto.CadastroRequest;
import com.example.demo.entrypoint.dto.TipoPessoaEnum;
import com.example.demo.exception.InvalidParametersException;
import jakarta.validation.ConstraintTarget;
import jakarta.validation.ConstraintViolation;

import jakarta.validation.Validator;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Component
public class CommonValidator {

    private static final String INVALID_TIPO_PESSOA= "O atributo tipoPessoa inválido!";

    @Autowired
    private Validator validator;

    public void validate(CadastroRequest request) throws BadRequestException {

        Map<String, Object> mapa = new HashMap<>();
        Class<?> grupoDeValidacao;

        isTipoPessoaPresent(request, mapa);

        if (isPj(request)) {
            grupoDeValidacao = CadastroCompletoGroup.class;
        } else {
            grupoDeValidacao = CadastroSimplesGroup.class;
        }

        Set<ConstraintViolation<CadastroRequest>> violations = validator.validate(request, grupoDeValidacao);

        if (!violations.isEmpty()) {
            violations.forEach(violation -> mapa.put(String.valueOf(violation.getPropertyPath()), violation.getMessage()));

            System.out.println("Errors: " + mapa) ;
            throw new InvalidParametersException("Invalid parameters", mapa);
            //throw new BadRequestException(mapa.toString());
        }

    }

    private static void isTipoPessoaPresent(CadastroRequest request, Map<String, Object> mapa) {
        boolean isNull = request.getTipoPessoa() == null;
        boolean isEmpty = request.getTipoPessoa().isEmpty();
        boolean isValid = TipoPessoaEnum.isValid(request.getTipoPessoa());

        if (isNull || isEmpty || !isValid) {
            mapa.put("tipoPessoa", INVALID_TIPO_PESSOA);
            throw new InvalidParametersException(INVALID_TIPO_PESSOA, mapa);
        }
    }

    private boolean isPj(CadastroRequest request) {
        return TipoPessoaEnum.FISICA.isPJ(request.getTipoPessoa());
    }
}
