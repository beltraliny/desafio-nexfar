package com.github.beltraliny.desafionexfar.services;

import com.github.beltraliny.desafionexfar.domain.Order;
import com.github.beltraliny.desafionexfar.utils.vo.ReportRequestVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportService {

    private static final Logger logger = Logger.getLogger(ReportService.class.getName());

    private final CsvFileService csvFileService;
    private final OrderService orderService;
    private final XlsFileService xlsFileService;

    public String generateCsvReport(ReportRequestVO reportRequestVO) {
        try {
            List<Order> orderList =  orderService.buildOrderListBasedOnReportRequestFilters(reportRequestVO);

            return switch (reportRequestVO.getType()) {
                case ORDER_SIMPLE -> csvFileService.buildSimpleReport(orderList);
                case ORDER_DETAILED -> csvFileService.buildDetailedReport(orderList);
            };
        } catch (Exception exception) {
            logger.severe("[ReportService.generateCsvReport] >> " + exception.getMessage());
            throw exception;
        }
    }

    public byte[] generateXlsReport(ReportRequestVO reportRequestVO) {
        try {
            List<Order> orderList =  orderService.buildOrderListBasedOnReportRequestFilters(reportRequestVO);

            return switch (reportRequestVO.getType()) {
                case ORDER_SIMPLE -> xlsFileService.buildSimpleReport(orderList);
                case ORDER_DETAILED -> xlsFileService.buildDetailedReport(orderList);
            };
        } catch (Exception exception) {
            logger.severe("[ReportService.generateXlsReport] >> " + exception.getMessage());
            throw exception;
        }
    }
}
