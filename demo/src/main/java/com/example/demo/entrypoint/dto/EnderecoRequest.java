package com.example.demo.entrypoint.dto;

import com.example.demo.entrypoint.annotations.EnumValidator;
import com.example.demo.entrypoint.validation.CadastroCompletoGroup;
import com.example.demo.entrypoint.validation.CadastroSimplesGroup;
import jakarta.validation.constraints.NotNull;
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
