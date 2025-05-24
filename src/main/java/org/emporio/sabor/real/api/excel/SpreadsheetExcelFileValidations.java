package org.emporio.sabor.real.api.excel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.emporio.sabor.real.api.exception.BadRequestException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;

@Slf4j
@RequiredArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class SpreadsheetExcelFileValidations {

    /**
     * "application/vnd.ms-excel" - xls
     * "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" - xlsx
     */
    private static final List<String> VALID_CONTENT_TYPES = asList(
            "application/vnd.ms-excel",
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

    public static String validateExcelFile(MultipartFile file) {

        if (file == null) {
            throw new BadRequestException("file is null");
        }

        var fileName = file.getOriginalFilename();

        if (!VALID_CONTENT_TYPES.contains(file.getContentType())) {
            throw new BadRequestException(format("%s file is not a valid excel file", fileName));
        }
        return fileName;
    }

    public static Workbook getWorkbook(MultipartFile file) throws IOException {

        var fileName = validateExcelFile(file);
        log.info("importing file {}", fileName);

        try (InputStream inputStream = file.getInputStream()) {
            return WorkbookFactory.create(inputStream);
        } catch (IOException e) {
            throw new IOException(format("Error to read %s file", fileName), e);
        }
    }
}
