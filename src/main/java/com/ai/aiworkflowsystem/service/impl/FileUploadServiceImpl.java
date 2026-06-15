package com.ai.aiworkflowsystem.service.impl;


import com.ai.aiworkflowsystem.dto.SalesDataDto;
import com.ai.aiworkflowsystem.entity.SalesData;
import com.ai.aiworkflowsystem.exception.FileProcessingException;
import com.ai.aiworkflowsystem.mapper.SalesDataMapper;
import com.ai.aiworkflowsystem.repository.SalesDataRepository;
import com.ai.aiworkflowsystem.service.FileUploadService;
import com.ai.aiworkflowsystem.utils.FileParserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {

    private final SalesDataRepository salesDataRepository;


    @Override
    public void uploadFile(MultipartFile file) {

        validateFile(file);

        String fileName =
                file.getOriginalFilename();

        List<SalesDataDto> dtoList;

        if (fileName.endsWith(".csv")) {

            dtoList =
                    FileParserUtil.parseCSV(file);

        } else {

            dtoList =
                    FileParserUtil.parseExcel(file);
        }

        List<SalesData> salesDataList =
                dtoList.stream()
                        .map(SalesDataMapper::toEntity)
                        .toList();

        salesDataRepository.saveAll(
                salesDataList
        );

        log.info(
                "Saved {} records",
                salesDataList.size()
        );

    }

    private void validateFile(
            MultipartFile file
    ) {
    if (file.isEmpty()) {

            throw new FileProcessingException(
                    "File is empty"
            );
        }

        String fileName =
                file.getOriginalFilename();

        if (fileName == null ||
                !(fileName.endsWith(".csv")
                        || fileName.endsWith(".xlsx"))) {

            throw new FileProcessingException(
                    "Only CSV and Excel files are allowed"
            );
        }

    }


}