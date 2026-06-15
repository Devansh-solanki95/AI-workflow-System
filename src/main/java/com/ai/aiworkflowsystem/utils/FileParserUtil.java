package com.ai.aiworkflowsystem.utils;

import com.ai.aiworkflowsystem.dto.SalesDataDto;
import com.ai.aiworkflowsystem.entity.SalesData;
import com.ai.aiworkflowsystem.exception.FileProcessingException;
import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class FileParserUtil {

    public static List<SalesDataDto> parseCSV(
            MultipartFile file
    ) {

        List<SalesDataDto> dtoList =
                new ArrayList<>();

        try (
                CSVReader csvReader = new CSVReader(
                        new InputStreamReader(
                                file.getInputStream()
                        )
                )
        ) {

            List<String[]> rows =
                    csvReader.readAll();

            for (int i = 1; i < rows.size(); i++) {

                String[] row = rows.get(i);

                SalesDataDto dto =
                        SalesDataDto.builder()
                                .date(LocalDate.parse(row[0]))
                                .region(row[1])
                                .leadSource(row[2])
                                .leadsGenerated(
                                        Integer.parseInt(row[3]))
                                .demoCalls(
                                        Integer.parseInt(row[4]))
                                .dealsClosed(
                                        Integer.parseInt(row[5]))
                                .revenue(
                                        Double.parseDouble(row[6]))
                                .marketingSpend(
                                        Double.parseDouble(row[7]))
                                .salesHoursSpent(
                                        Integer.parseInt(row[8]))
                                .build();

                dtoList.add(dto);
            }

        } catch (Exception ex) {

            log.error("CSV parsing failed", ex);

            throw new FileProcessingException(
                    "Failed to parse CSV file"
            );
        }

        return dtoList;
    }

    public static List<SalesDataDto> parseExcel(
            MultipartFile file
    ) {

        List<SalesDataDto> dtoList =
                new ArrayList<>();

        try (
                Workbook workbook =
                        WorkbookFactory.create(
                                file.getInputStream()
                        );
        ) {

            Sheet sheet =
                    workbook.getSheetAt(0);

            for (int i = 1;
                 i <= sheet.getLastRowNum();
                 i++) {

                Row row = sheet.getRow(i);

                SalesDataDto dto =
                        SalesDataDto.builder()
                                .date(
                                        row.getCell(0)
                                                .getLocalDateTimeCellValue()
                                                .toLocalDate()
                                )
                                .region(
                                        row.getCell(1)
                                                .getStringCellValue()
                                )
                                .leadSource(
                                        row.getCell(2)
                                                .getStringCellValue()
                                )
                                .leadsGenerated(
                                        (int) row.getCell(3)
                                                .getNumericCellValue()
                                )
                                .demoCalls(
                                        (int) row.getCell(4)
                                                .getNumericCellValue()
                                )
                                .dealsClosed(
                                        (int) row.getCell(5)
                                                .getNumericCellValue()
                                )
                                .revenue(
                                        row.getCell(6)
                                                .getNumericCellValue()
                                )
                                .marketingSpend(
                                        row.getCell(7)
                                                .getNumericCellValue()
                                )
                                .salesHoursSpent(
                                        (int) row.getCell(8)
                                                .getNumericCellValue()
                                )
                                .build();

                dtoList.add(dto);
            }

        } catch (Exception ex) {

            log.error("Excel parsing failed", ex);

            throw new FileProcessingException(
                    "Failed to parse Excel file"
            );
        }

        return dtoList;
    }

}
