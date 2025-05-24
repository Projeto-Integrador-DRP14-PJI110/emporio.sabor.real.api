package org.emporio.sabor.real.api.excel;

import lombok.AllArgsConstructor;

import static org.apache.commons.lang3.StringUtils.isBlank;

@AllArgsConstructor
public class StringCellType implements CellDefinition<String> {

    @Override
    public String getValue(org.apache.poi.ss.usermodel.Cell cell) {
        if (cell == null || isBlank(cell.toString())) {
            return null;
        }

        return cell.getStringCellValue().trim();
    }

}
