package com.example.demo.entrypoint.dto;

import com.example.demo.entrypoint.annotations.EnumValidator;
import com.example.demo.entrypoint.validation.CadastroCompletoGroup;
import com.example.demo.entrypoint.validation.CadastroSimplesGroup;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.cfg.CoercionConfig;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
