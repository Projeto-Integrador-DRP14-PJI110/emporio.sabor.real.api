package org.emporio.sabor.real.api.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "estoque", catalog = "emporio_sabor_real", schema = "emporio_sabor_real")
public class EstoqueEntity {

    @Id
    @Column(name = "id", unique = true)
    Long id;

    @Column(name = "produto", nullable = false)
    String produto;

    @Column(name = "quantidade", nullable = false)
    Integer quantidade;

    @Column(name = "validade", nullable = false)
    LocalDate validade;

    @Column(name = "categoria", nullable = false)
    String categoria;

    @Column(name = "data_compra")
    LocalDate dataCompra;

    @Column(name = "preco_compra", nullable = false)
    BigDecimal precoCompra;

    @Column(name = "preco_venda", nullable = false)
    BigDecimal precoVenda;

    @Column(name = "fornecedor", nullable = false)
    String fornecedor;

    @Column(name = "descricao")
    String descricao;
}