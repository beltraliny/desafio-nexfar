package com.github.beltraliny.desafionexfar.services;

import com.github.beltraliny.desafionexfar.domain.Order;
import com.github.beltraliny.desafionexfar.domain.OrderItem;
import com.github.beltraliny.desafionexfar.utils.DateUtil;
import com.github.beltraliny.desafionexfar.utils.exception.ReportIntegrityException;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CsvFileService {

    private static final Logger logger = Logger.getLogger(CsvFileService.class.getName());

    public String buildSimpleReport(List<Order> orderList) {
        try {
            StringWriter stringWriter = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(stringWriter);

            String[] header = {
                    "_id",
                    "client.cnpj",
                    "client.name",
                    "createdAt",
                    "status",
                    "netTotal",
                    "totalWithTaxes"
            };

            csvWriter.writeNext(header);

            for (Order order : orderList) {
                String[] data = {
                        order.getId(),
                        order.getClient().getName(),
                        DateUtil.buildStringFromInstant(order.getCreatedAt()),
                        order.getStatus(),
                        String.valueOf(order.getNetTotal()),
                        String.valueOf(order.getTotalWithTaxes())
                };

                csvWriter.writeNext(data);
            }
            csvWriter.close();
            return stringWriter.toString();

        } catch (IOException ioException) {
            logger.severe("[CsvFileService.buildSimpleReport] >> " + ioException.getMessage());
            throw new ReportIntegrityException();
        }
    }

    public String buildDetailedReport(List<Order> orderList) {
        try {
            StringWriter stringWriter = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(stringWriter);

            String[] header = {
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

            csvWriter.writeNext(header);

            for (Order order : orderList) {
                for (OrderItem orderItem : order.getItems()) {
                    String[] data = {
                            order.getId(),
                            order.getClient().getCnpj(),
                            order.getClient().getName(),
                            DateUtil.buildStringFromInstant(order.getCreatedAt()),
                            order.getStatus(),
                            orderItem.getProduct().getSku(),
                            orderItem.getProduct().getName(),
                            String.valueOf(orderItem.getQuantity()),
                            String.valueOf(orderItem.getPrice()),
                            String.valueOf(orderItem.getFinalPrice())
                    };

                    csvWriter.writeNext(data);

                }
            }

            csvWriter.close();
            return stringWriter.toString();
        } catch (IOException ioException) {
            logger.severe("[CsvFileService.buildDetailedReport] >> " + ioException.getMessage());
            throw new ReportIntegrityException();
        }
    }
}
