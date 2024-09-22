package com.example.demo.entrypoint;

import com.example.demo.entrypoint.dto.CadastroRequest;
import com.example.demo.entrypoint.validation.CommonValidator;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired
    private CommonValidator validator;

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastroRequest request) throws BadRequestException {

        validator.validate(request);

        return ResponseEntity.ok("funcionou!");
    }

}
