package org.emporio.sabor.real.api.excel;

import org.apache.poi.ss.usermodel.Cell;

public interface CellDefinition<T> {

    T getValue(Cell cell);

}
