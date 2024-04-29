package com.github.beltraliny.desafionexfar.services;

import com.github.beltraliny.desafionexfar.domain.Order;
import com.github.beltraliny.desafionexfar.domain.OrderItem;
import com.github.beltraliny.desafionexfar.utils.DateUtil;
import com.github.beltraliny.desafionexfar.utils.exception.ReportIntegrityException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@Service
public class XlsFileService {

    private static final Logger logger = Logger.getLogger(XlsFileService.class.getName());

    public byte[] buildSimpleReport(List<Order> orderList) {
        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet("Orders");

            Row headerRow = sheet.createRow(0);
            String[] columns = {
                    "_id",
                    "client.cnpj",
                    "client.name",
                    "createdAt",
                    "status",
                    "netTotal",
                    "totalWithTaxes"
            };

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            int rowNum = 1;
            for (Order order : orderList) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(order.getId());
                row.createCell(1).setCellValue(order.getClient().getCnpj());
                row.createCell(2).setCellValue(order.getClient().getName());
                row.createCell(3).setCellValue(DateUtil.buildStringFromInstant(order.getCreatedAt()));
                row.createCell(4).setCellValue(order.getStatus());
                row.createCell(5).setCellValue(order.getNetTotal());
                row.createCell(6).setCellValue(order.getTotalWithTaxes());
            }

            workbook.write(outputStream);
            workbook.close();

            return outputStream.toByteArray();
        } catch (IOException ioException) {
            logger.severe("[XlsFileService.buildSimpleReport] >> " + ioException.getMessage());
            throw new ReportIntegrityException();
        }
    }

    public byte[] buildDetailedReport(List<Order> orderList) {
        try {
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            Sheet sheet = workbook.createSheet("Orders");

            Row headerRow = sheet.createRow(0);
            String[] columns = {
                    "order._id",
                    "order.client.cnpj",
                    "order.client.name",
                    "order.createdAt",
                    "order.status",
                    "order.items.product.sku",
                    "order.items.product.name",
                    "order.items.quantity",
                    "order.items.finalPrice.price",
                    "order.items.finalPrice.finalPrice"
            };

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            int rowNum = 1;
            for (Order order : orderList) {
                for (OrderItem orderItem : order.getItems()) {
                    Row row = sheet.createRow(rowNum++);

                    row.createCell(0).setCellValue(order.getId());
                    row.createCell(1).setCellValue(order.getClient().getCnpj());
                    row.createCell(2).setCellValue(order.getClient().getName());
                    row.createCell(3).setCellValue(DateUtil.buildStringFromInstant(order.getCreatedAt()));
                    row.createCell(4).setCellValue(order.getStatus());
                    row.createCell(5).setCellValue(orderItem.getProduct().getSku());
                    row.createCell(6).setCellValue(orderItem.getProduct().getName());
                    row.createCell(7).setCellValue(String.valueOf(orderItem.getQuantity()));
                    row.createCell(8).setCellValue(String.valueOf(orderItem.getPrice()));
                    row.createCell(9).setCellValue(String.valueOf(orderItem.getFinalPrice()));
                }
            }

            workbook.write(outputStream);
            workbook.close();

            return outputStream.toByteArray();
        } catch (IOException ioException) {
            logger.severe("[XlsFileService.buildDetailedReport] >> " + ioException.getMessage());
            throw new ReportIntegrityException();
        }
    }
}
