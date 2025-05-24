package org.emporio.sabor.real.api.service;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.emporio.sabor.real.api.domain.model.Estoque;
import org.emporio.sabor.real.api.excel.EstoqueColumnDefinition;
import org.emporio.sabor.real.api.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.IntStream;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@AllArgsConstructor
public class UploadService {

    private final EstoqueService estoqueService;

    public void uploadFile(Workbook file) {
        validateSpreadsheet(file);

        extractEstoqueFromSpreadsheet(file);
    }

    private void validateSpreadsheet(Workbook file) {
        if (isEmpty(file)) {
            throw new BadRequestException("Excel file is required");
        }
        validateColumns(file);

    }

    private void validateColumns(Workbook file) {
        var sheet = file.getSheetAt(0);
        var expectedColumns = EstoqueColumnDefinition.EXPECTED_COLUMNS;
        Row columnsNamesRow = sheet.getRow(0);

        if (columnsNamesRow == null) {
            throw new BadRequestException("Columns names row is required");
        }

        for (int i = 0; i < expectedColumns.size(); i++) {
            Cell cell = columnsNamesRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            var expectedColumnName = expectedColumns.get(i).column().getName();
            var columnName = cell.getStringCellValue().trim();

            if (!columnName.equals(expectedColumnName)) {
                throw new BadRequestException("Invalid column name");
            }
        }
    }

    private void extractEstoqueFromSpreadsheet(Workbook spreadsheet) {

        var sheet = spreadsheet.getSheetAt(0);

        IntStream.rangeClosed(1, sheet.getLastRowNum()).forEach(i -> {
            var row = sheet.getRow(i);

            if (row == null) {
                return;
            }
            processRow(row);
        });

    }

    private void processRow(Row row) {

        var cceeAgentMeterPoint = buildEstoque(row);

        if (!validateEstoque(cceeAgentMeterPoint)) {
            estoqueService.salvar(cceeAgentMeterPoint);;
        }
    }

    private Estoque buildEstoque(Row row) {

        return Estoque.builder()
                .produto((String) getCellValue(EstoqueColumnDefinition.PRODUTO, row))
                .quantidade((Integer) getCellValue(EstoqueColumnDefinition.QUANTIDADE, row))
                .validade((LocalDate) getCellValue(EstoqueColumnDefinition.VALIDADE, row))
                .categoria((String) getCellValue(EstoqueColumnDefinition.CATEGORIA, row))
                .dataCompra((LocalDate) getCellValue(EstoqueColumnDefinition.DATA_COMPRA, row))
                .precoCompra((BigDecimal) getCellValue(EstoqueColumnDefinition.PRECO_COMPRA, row))
                .precoVenda((BigDecimal) getCellValue(EstoqueColumnDefinition.PRECO_VENDA, row))
                .fornecedor((String) getCellValue(EstoqueColumnDefinition.FORNECEDOR, row))
                .descricao((String) getCellValue(EstoqueColumnDefinition.DESCRICAO, row))
                .produtoDisponivel(true)
                .build();
    }

    private Boolean validateEstoque(Estoque estoque) {
        return estoque.getProduto() == null && estoque.getQuantidade() == null && estoque.getValidade() == null
                && estoque.getCategoria() == null && estoque.getDataCompra() == null && estoque.getPrecoCompra() == null
                && estoque.getPrecoVenda() == null && estoque.getFornecedor() == null && estoque.getProdutoDisponivel();
    }

    private Object getCellValue(
            EstoqueColumnDefinition estoqueColumnDefinition, Row row) {
        var cellDefinition = estoqueColumnDefinition.column().getCellDefinition();
        var cell = row.getCell(estoqueColumnDefinition.index());
        return cellDefinition.getValue(cell);
    }
}