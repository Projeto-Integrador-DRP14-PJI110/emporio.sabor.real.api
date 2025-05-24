package org.emporio.sabor.real.api.excel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EstoqueColumnsDefinitionEnum {

    PRODUTO("produto", new StringCellType()),
    QUANTIDADE("quantidade", new IntegerCellType()),
    VALIDADE("validade", new LocalDateCellType()),
    CATEGORIA("categoria", new StringCellType()),
    DATA_COMPRA("dataCompra", new LocalDateCellType()),
    PRECO_COMPRA("precoCompra", new BigDecimalCellType()),
    PRECO_VENDA("precoVenda", new BigDecimalCellType()),
    FORNECEDOR("fornecedor", new StringCellType()),
    DESCRICAO("descricao", new StringCellType()),
    PRODUTO_DIPONIVEL("produtoDisponivel", new BooleanCellType()),;

    private final String name;
    @SuppressWarnings("rawtypes")
    private final CellDefinition cellDefinition;
}
