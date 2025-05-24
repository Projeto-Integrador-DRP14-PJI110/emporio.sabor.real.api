package org.emporio.sabor.real.api.excel;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import java.time.LocalDate;

import static org.apache.commons.lang3.StringUtils.isBlank;

@AllArgsConstructor
public class IntegerCellType implements CellDefinition<Integer> {

    @Override
    public Integer getValue(Cell cell) {
        if (cell == null || isBlank(cell.toString())) {
            return null;
        }

        if (cell.getCellType() == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            String cellValue = cell.getStringCellValue();
            if (isBlank(cellValue)) {
                return null;
            }
            try {
                return Integer.parseInt(cellValue);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid integer value: " + cellValue, e);
            }
        } else {
            throw new IllegalArgumentException("Invalid cell type for integer: " + cell.getCellType());
        }
    }
}
