package com.example.demo.application.annotations;

import com.example.demo.application.validation.CommonValidator;
import com.example.demo.infra.entrypoint.dto.CadastroRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PayloadValidatorV2Impl implements ConstraintValidator<PayloadValidatorV2, CadastroRequest> {

    @Autowired
    private CommonValidator commonValidator;

    @Override
    public boolean isValid(CadastroRequest request, ConstraintValidatorContext context) {

        this.commonValidator.validate(request);

        return true;
    }

}
