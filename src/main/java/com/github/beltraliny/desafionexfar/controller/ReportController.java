package com.github.beltraliny.desafionexfar.controller;

import com.github.beltraliny.desafionexfar.controller.dto.ReportRequestDTO;
import com.github.beltraliny.desafionexfar.services.ReportService;
import com.github.beltraliny.desafionexfar.utils.vo.ReportRequestVO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generateReport(@RequestBody ReportRequestDTO reportRequestDTO, HttpServletResponse response) {
        ReportRequestVO reportRequestVO = new ReportRequestVO(reportRequestDTO);

        if (reportRequestVO.getFormat() != null && reportRequestVO.getType() != null) {
            if (reportRequestVO.getFormat().isCsv()) return buildCsvReport(reportRequestVO, response);
            if (reportRequestVO.getFormat().isXls()) return buildXlsReport(reportRequestVO, response);
        }

        return ResponseEntity.badRequest().build();
    }

    private ResponseEntity<byte[]> buildCsvReport(@RequestBody ReportRequestVO reportRequestVO, HttpServletResponse response) {
        String csvContent = reportService.generateCsvReport(reportRequestVO);

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename="+ reportRequestVO.getReportName() +".csv");

        try (PrintWriter writer = response.getWriter()) {
            writer.write(csvContent);
        } catch (IOException exception) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().build();
    }

    private ResponseEntity<byte[]> buildXlsReport(@RequestBody ReportRequestVO reportRequestVO, HttpServletResponse response) {
        byte[] xlsContent = reportService.generateXlsReport(reportRequestVO);

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename="+ reportRequestVO.getReportName() +".xls");

        return ResponseEntity.ok().body(xlsContent);
    }
}
