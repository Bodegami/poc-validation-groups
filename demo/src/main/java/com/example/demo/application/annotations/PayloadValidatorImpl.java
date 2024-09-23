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

    @Override
    public boolean isValid(CadastroRequest request, ConstraintValidatorContext context) {
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
            throw new InvalidParametersException("Invalid parameters", mapa);
        }

        return true;
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
