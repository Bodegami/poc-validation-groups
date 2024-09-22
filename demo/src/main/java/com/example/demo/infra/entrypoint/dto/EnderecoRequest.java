package com.example.demo.infra.entrypoint.dto;

import com.example.demo.application.annotations.EnumValidator;
import com.example.demo.application.validation.CadastroCompletoGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequest {

    private String rua;
    private String bairro;
    private Integer numero;
    private String cidade;
    @EnumValidator(
            enumClazz = TipoEstadoEnum.class,
            message = "Tipo Estado deve ser: ['SP', 'RJ', 'MG']",
            groups = {CadastroCompletoGroup.class}
    )
    private String estado;
}
