package org.emporio.sabor.real.api.excel;

import java.util.List;

public record EstoqueColumnDefinition(EstoqueColumnsDefinitionEnum column, int index) {

    public static final EstoqueColumnDefinition PRODUTO = new EstoqueColumnDefinition(EstoqueColumnsDefinitionEnum.PRODUTO, 0);
    public static final EstoqueColumnDefinition QUANTIDADE = new EstoqueColumnDefinition(EstoqueColumnsDefinitionEnum.QUANTIDADE, 1);
    public static final EstoqueColumnDefinition VALIDADE = new EstoqueColumnDefinition(EstoqueColumnsDefinitionEnum.VALIDADE, 2);
    public static final EstoqueColumnDefinition CATEGORIA = new EstoqueColumnDefinition(EstoqueColumnsDefinitionEnum.CATEGORIA, 3);
    public static final EstoqueColumnDefinition DATA_COMPRA = new EstoqueColumnDefinition(EstoqueColumnsDefinitionEnum.DATA_COMPRA, 4);
    public static final EstoqueColumnDefinition PRECO_COMPRA = new EstoqueColumnDefinition(EstoqueColumnsDefinitionEnum.PRECO_COMPRA, 5);
    public static final EstoqueColumnDefinition PRECO_VENDA = new EstoqueColumnDefinition(EstoqueColumnsDefinitionEnum.PRECO_VENDA, 6);
    public static final EstoqueColumnDefinition FORNECEDOR = new EstoqueColumnDefinition(EstoqueColumnsDefinitionEnum.FORNECEDOR, 7);
    public static final EstoqueColumnDefinition DESCRICAO = new EstoqueColumnDefinition(EstoqueColumnsDefinitionEnum.DESCRICAO, 8);
    public static final EstoqueColumnDefinition PRODUTO_DIPONIVEL = new EstoqueColumnDefinition(EstoqueColumnsDefinitionEnum.PRODUTO_DIPONIVEL, 9);

    public static final List<EstoqueColumnDefinition> EXPECTED_COLUMNS = List.of(
            PRODUTO,
            QUANTIDADE,
            VALIDADE,
            CATEGORIA,
            DATA_COMPRA,
            PRECO_COMPRA,
            PRECO_VENDA,
            FORNECEDOR,
            DESCRICAO,
            PRODUTO_DIPONIVEL
    );
}
