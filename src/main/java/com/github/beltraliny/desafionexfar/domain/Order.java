package com.github.beltraliny.desafionexfar.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "order")
public class Order {

    @Id
    private String id;
    private Client client;
    private Instant createdAt;
    private String status;
    private double netTotal;
    private double totalWithTaxes;
    private List<OrderItem> items;
}
