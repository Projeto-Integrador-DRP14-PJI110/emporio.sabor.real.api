package org.emporio.sabor.real.api.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estoque {

    private Long id;

    private String produto;

    private Integer quantidade;

    private LocalDate validade;

    private String categoria;

    private LocalDate dataCompra;

    private BigDecimal precoCompra;

    private BigDecimal precoVenda;

    private String fornecedor;

    private String descricao;

    private Boolean produtoDisponivel;

}
