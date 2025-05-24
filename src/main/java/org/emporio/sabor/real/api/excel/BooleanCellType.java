package org.emporio.sabor.real.api.excel;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

@AllArgsConstructor
public class BooleanCellType implements CellDefinition<Boolean> {

    @Override
    public Boolean getValue(Cell cell) {
        if (cell == null) {
            return null;
        }

        if (cell.getCellType() == CellType.BOOLEAN) {
            return cell.getBooleanCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            String stringValue = cell.getStringCellValue();
            return Boolean.parseBoolean(stringValue);
        } else {
            throw new IllegalArgumentException("Invalid cell type for boolean value");
        }
    }
}
