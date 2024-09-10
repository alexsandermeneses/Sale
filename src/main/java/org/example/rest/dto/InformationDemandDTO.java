package org.example.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformationDemandDTO {

    private Integer code;
    private String cpf;
    private String clientName;
    private BigDecimal total;
    private String dataOrder;
    private List<InformationItemDemandDTO> items;
}