package org.emporio.sabor.real.api.excel;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.emporio.sabor.real.api.exception.BadRequestException;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.emporio.sabor.real.api.excel.SheetHelper.getColumnName;

@AllArgsConstructor
public class LocalDateCellType implements CellDefinition<LocalDate> {

    @Override
    public LocalDate getValue(org.apache.poi.ss.usermodel.Cell cell) {
        if (cell == null) {
            return null;
        }

        if (cell.getCellType() == CellType.BLANK || isBlank(cell.toString())) {

            cell = getMergedCell(cell);
            if (cell == null || isBlank(cell.toString())) {
                return null;
            }
        }

        if (cell.getCellType() == CellType.STRING) {
            try {

                String cellValue = cell.getStringCellValue().trim();
                return LocalDate.parse(cellValue);

            } catch (Exception e) {
                throw getError(cell);
            }

        } else if (cell.getCellType() == CellType.NUMERIC) {

            if (DateUtil.isCellDateFormatted(cell)) {

                Date javaDate = cell.getDateCellValue();
                return javaDate.toInstant()
                        .atZone(DateFormat.getDateInstance().getTimeZone().toZoneId())
                        .toLocalDate();

            } else {
                throw getError(cell);
            }
        } else {
            throw getError(cell);
        }

    }

    public static org.apache.poi.ss.usermodel.Cell getMergedCell(
            org.apache.poi.ss.usermodel.Cell cell) {
        Sheet sheet = cell.getSheet();
        int row = cell.getRowIndex();
        int col = cell.getColumnIndex();
        for (CellRangeAddress mergedRegion : sheet.getMergedRegions()) {
            if (row >= mergedRegion.getFirstRow() && row <= mergedRegion.getLastRow() &&
                    col >= mergedRegion.getFirstColumn() && col <= mergedRegion.getLastColumn()) {
                // Return the top-left cell of the merged region
                Row firstRow = sheet.getRow(mergedRegion.getFirstRow());
                return firstRow.getCell(mergedRegion.getFirstColumn());
            }
        }
        return null;
    }

    private static BadRequestException getError(org.apache.poi.ss.usermodel.Cell cell) {
        var sheetName = getColumnName(cell.getSheet().getWorkbook(), cell.getColumnIndex(), cell.getRowIndex());
        String cellAddress = cell.getAddress().formatAsString();
        return new BadRequestException("Invalid cell %s date format [%s]");
    }
}
