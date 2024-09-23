package com.example.demo.application.annotations;

import com.example.demo.application.validation.CadastroCompletoGroup;
import com.example.demo.application.validation.CadastroSimplesGroup;
import com.example.demo.core.exception.InvalidParametersException;
import com.example.demo.infra.entrypoint.dto.CadastroRequest;
import com.example.demo.infra.entrypoint.dto.TipoPessoaEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PayloadValidatorImpl implements ConstraintValidator<PayloadValidator, CadastroRequest> {

    @Autowired
    private Validator validator;

    private static final String INVALID_TIPO_PESSOA= "O atributo tipoPessoa inválido!";
    private static final Class<CadastroSimplesGroup> GRUPO_PF = CadastroSimplesGroup.class;
    private static final Class<CadastroCompletoGroup> GRUPO_PJ = CadastroCompletoGroup.class;

    @Override
    public boolean isValid(CadastroRequest request, ConstraintValidatorContext context) {
        isTipoPessoaPresent(request);

        Map<String, Object> mapa = new HashMap<>();
        Class<?> grupoDeValidacao;

        grupoDeValidacao = isPj(request) ? GRUPO_PJ : GRUPO_PF;

        Set<ConstraintViolation<CadastroRequest>> violations = validator.validate(request, grupoDeValidacao);

        if (!violations.isEmpty()) {
            violations.forEach(violation -> mapa.put(String.valueOf(violation.getPropertyPath()), violation.getMessage()));
            throw new InvalidParametersException("Invalid parameters", mapa);
        }

        return true;
    }

    private static void isTipoPessoaPresent(CadastroRequest request) {

        if (request.getTipoPessoa() == null || request.getTipoPessoa().isBlank()
                || !TipoPessoaEnum.isValid(request.getTipoPessoa())) {

            throw new InvalidParametersException(INVALID_TIPO_PESSOA, Map.of("tipoPessoa", INVALID_TIPO_PESSOA));
        }
    }

    private boolean isPj(CadastroRequest request) {
        return TipoPessoaEnum.FISICA.isPJ(request.getTipoPessoa());
    }

}
