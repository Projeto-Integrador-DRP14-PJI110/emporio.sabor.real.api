package org.emporio.sabor.real.api.excel;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.emporio.sabor.real.api.exception.BadRequestException;

@RequiredArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class SheetHelper {

    public static String getColumnName(Workbook spreadsheet, int columnIndex, int initialRow) {

        try {
            Sheet sheet = spreadsheet.getSheetAt(0);
            if (sheet == null) {
                throw new BadRequestException("Spreadsheet does not contain any sheets");
            }
            Row row = sheet.getRow(initialRow);
            if (row == null) {
                throw new BadRequestException("The provided row is null");
            }
            Cell cell = row.getCell(columnIndex);
            if (cell == null) {
                throw new BadRequestException("Column index is out of bounds");
            }
            if (cell.getCellType() != CellType.STRING) {
                throw new BadRequestException("Cell at column index is not a string cell");
            }
            return cell.getStringCellValue();
        } catch (IllegalArgumentException | IllegalStateException | IndexOutOfBoundsException e) {
            throw new RuntimeException(e);
        }
    }
}
