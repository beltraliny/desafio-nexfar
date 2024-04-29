package com.github.beltraliny.desafionexfar.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "client")
public class Client {

    @Id
    String id;
    String name;
    String cnpj;
}
