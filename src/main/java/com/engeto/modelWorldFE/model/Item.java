package com.engeto.modelWorldFE.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Item {
    Long id;
    int partNo;
    String name;
    String description;
    Boolean isForSale;
    BigDecimal price;
}
