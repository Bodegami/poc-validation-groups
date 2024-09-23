package com.example.demo.infra.entrypoint.dto;

import com.example.demo.application.annotations.EnumValidator;
import com.example.demo.application.annotations.PayloadValidator;
import com.example.demo.application.annotations.PayloadValidatorV2;
import com.example.demo.application.validation.CadastroCompletoGroup;
import com.example.demo.application.validation.CadastroSimplesGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PayloadValidator
//@PayloadValidatorV2
public class CadastroRequest {

    @NotBlank(message = "Deu ruim", groups = {CadastroSimplesGroup.class})
    private String nome;
    @NotBlank(message = "Deu ruim", groups = {CadastroCompletoGroup.class})
    @Email(message = "Deu ruim", groups = {CadastroCompletoGroup.class})
    private String email;
    @NotNull(message = "Deu ruim", groups = {CadastroSimplesGroup.class})
    @Min(value = 1, message = "Deu ruim", groups = {CadastroSimplesGroup.class})
    @Positive(message = "Deu ruim", groups = {CadastroSimplesGroup.class})
    private BigDecimal documento;
    @EnumValidator(
            enumClazz = TipoPessoaEnum.class,
            message = "Tipo Pessoa deve ser: ['FISICA', 'JURIDICA]",
            groups = {CadastroSimplesGroup.class, CadastroCompletoGroup.class}
    )
    private String tipoPessoa;
    @Valid
    private EnderecoRequest endereco;

}
