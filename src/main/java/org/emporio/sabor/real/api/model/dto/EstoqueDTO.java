package org.emporio.sabor.real.api.model.dto;

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
public class EstoqueDTO {

    Long id;

    String produto;

    Integer quantidade;

    LocalDate validade;

    String categoria;

    LocalDate dataCompra;

    BigDecimal precoCompra;

    BigDecimal precoVenda;

    String fornecedor;

    String descricao;

}
