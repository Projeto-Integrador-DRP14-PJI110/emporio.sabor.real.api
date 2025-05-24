package org.emporio.sabor.real.api.excel;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.emporio.sabor.real.api.exception.BadRequestException;

import java.math.BigDecimal;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.emporio.sabor.real.api.excel.SheetHelper.getColumnName;

@AllArgsConstructor
@Slf4j
public class BigDecimalCellType implements CellDefinition<BigDecimal> {

    @Override
    public BigDecimal getValue(Cell cell) {
        if (cell == null || isBlank(cell.toString())) {
            return null;
        }

        if (cell.getCellType() != CellType.NUMERIC) {
            var sheetName = getColumnName(cell.getSheet().getWorkbook(), cell.getColumnIndex(), cell.getRowIndex());
            var cellAddress = cell.getAddress().formatAsString();
            throw new BadRequestException("Cell is expected to be a Big Decimal");
        }

        return BigDecimal.valueOf(cell.getNumericCellValue());
    }

}
