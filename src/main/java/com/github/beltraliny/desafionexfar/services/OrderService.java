package com.github.beltraliny.desafionexfar.services;

import com.github.beltraliny.desafionexfar.domain.Order;
import com.github.beltraliny.desafionexfar.utils.vo.ReportRequestVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final MongoTemplate mongoTemplate;

    public List<Order> buildOrderListBasedOnReportRequestFilters(ReportRequestVO reportRequestVO) {

        Criteria criteria = new Criteria();

        if (reportRequestVO.getCnpj() != null) {
            criteria.and("client.cnpj").is(reportRequestVO.getCnpj());
        }

        if (reportRequestVO.getCreatedAtStartDate() != null &&
                reportRequestVO.getCreatedAtEndDate() != null
        ) {
            criteria.and("createdAt").gte(reportRequestVO.getCreatedAtStartDate()).lte(reportRequestVO.getCreatedAtEndDate());
        } else if (reportRequestVO.getCreatedAtStartDate() != null) {
            criteria.and("createdAt").gte(reportRequestVO.getCreatedAtStartDate());
        } else if (reportRequestVO.getCreatedAtEndDate() != null) {
            criteria.and("createdAt").lte(reportRequestVO.getCreatedAtEndDate());
        }

        if (reportRequestVO.getStatus() != null) {
            criteria.and("status").is(reportRequestVO.getStatus());
        }

        if (reportRequestVO.getMinNetTotal() != null) {
            criteria.and("netTotal").gte(reportRequestVO.getMinNetTotal());
        }

        if (reportRequestVO.getMaxNetTotal() != null) {
            criteria.and("netTotal").lte(reportRequestVO.getMaxNetTotal());
        }

        Query query = new Query(criteria);
        return  mongoTemplate.find(query, Order.class);
    }
}
