package org.emporio.sabor.real.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.emporio.sabor.real.api.excel.SpreadsheetExcelFileValidations;
import org.emporio.sabor.real.api.service.UploadService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "api/v1/upload")
@AllArgsConstructor
@Validated
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Upload", description = "Upload API")
public class UploadController {

    private final UploadService uploadService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Realiza upload de arquivos", description = "Realiza upload de arquivos para salvar produtos no sistema de estoque",
            tags = {"Upload"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Realiza upload de arquivos")})
    public ResponseEntity<Void> uploadFile(@Valid @RequestParam(value = "file") MultipartFile file) throws IOException {

        uploadService.uploadFile(getExcelWorkbook(file));
        return ResponseEntity.ok().build();
    }

    private Workbook getExcelWorkbook(MultipartFile file) throws IOException {
        log.info("Converting uploaded file to excel workbook");
        return SpreadsheetExcelFileValidations.getWorkbook(file);
    }
}
