package org.example.rest.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformationItemDemandDTO {
    private String productDescription;
    private BigDecimal unitPrice;
    private  Integer quantity;
}
