package com.github.beltraliny.desafionexfar.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "order_item")
public class OrderItem {

    @Id
    private String id;
    private Product product;
    private long quantity;
    private double price;
    private double finalPrice;
}
