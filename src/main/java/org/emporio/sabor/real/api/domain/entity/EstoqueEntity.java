package org.emporio.sabor.real.api.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import org.hibernate.annotations.Where;

@Entity
@Data
@Table(name = "estoque_produtos", catalog = "emporio_sabor_real", schema = "emporio_sabor_real")
@Where(clause = "produto_disponivel = true")
public class EstoqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "produto", nullable = false)
    private String produto;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "validade", nullable = false)
    private LocalDate validade;

    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Column(name = "data_compra")
    private LocalDate dataCompra;

    @Column(name = "preco_compra", nullable = false)
    private BigDecimal precoCompra;

    @Column(name = "preco_venda", nullable = false)
    private BigDecimal precoVenda;

    @Column(name = "fornecedor")
    private String fornecedor;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "produto_disponivel", nullable = false, columnDefinition = "boolean default true")
    private Boolean produtoDisponivel;
}