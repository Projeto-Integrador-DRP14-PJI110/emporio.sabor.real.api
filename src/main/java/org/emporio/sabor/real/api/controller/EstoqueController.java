package org.emporio.sabor.real.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "api/v1/estoque", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Estoque Emporio Sabor Real", description = "Estoque Emporio Sabor Real API")
public class EstoqueController {

}
