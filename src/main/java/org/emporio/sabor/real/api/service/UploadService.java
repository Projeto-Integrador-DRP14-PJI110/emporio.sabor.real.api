package org.emporio.sabor.real.api.service;

import lombok.AllArgsConstructor;
import org.emporio.sabor.real.api.domain.model.Estoque;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class UploadService {

    private final EstoqueService estoqueService;

    public void upload(byte[] file) {

        var estoque = new ArrayList<Estoque>();
        estoque.add(new Estoque(null, "Queijo Minas", 10, LocalDate.parse("2023-10-01"), "Queijos", LocalDate.parse("2023-10-01"), BigDecimal.valueOf(50.00), BigDecimal.valueOf(100.00), "Queijo Minas Fornecimento", "", true));
        estoque.add(new Estoque(null, "Queijo Prato", 20, LocalDate.parse("2023-10-01"), "Queijos", LocalDate.parse("2023-10-01"), BigDecimal.valueOf(50.00), BigDecimal.valueOf(100.00), "Queijo Prato Fornecimento", "", true));
        estoque.add(new Estoque(null, "Queijo Parmesão", 30, LocalDate.parse("2023-10-01"), "Queijos", LocalDate.parse("2023-10-01"), BigDecimal.valueOf(50.00), BigDecimal.valueOf(100.00), "Queijo Parmesão Fornecimento", "", true));
        estoque.add(new Estoque(null, "Queijo Gouda", 40, LocalDate.parse("2023-10-01"), "Queijos", LocalDate.parse("2023-10-01"), BigDecimal.valueOf(50.00), BigDecimal.valueOf(100.00), "Queijo Gouda Fornecimento", "", true));
        estoque.add(new Estoque(null, "Queijo Brie", 50, LocalDate.parse("2023-10-01"), "Queijos", LocalDate.parse("2023-10-01"), BigDecimal.valueOf(50.00), BigDecimal.valueOf(100.00), "Queijo Brie Fornecimento", "", true));

        for (Estoque e : estoque) {
            estoqueService.salvar(e);
        }
    }
}