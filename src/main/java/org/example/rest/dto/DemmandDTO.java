package org.example.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemmandDTO {
    private Integer client;
    private BigDecimal total;
    private List<ItemsOrderDTO> itemsOrderDTOS;
}