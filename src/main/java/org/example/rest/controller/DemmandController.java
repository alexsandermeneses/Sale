package org.example.rest.controller;

import org.example.Service.DemmandService;
import org.example.domain.entity.Demmand;
import org.example.domain.entity.ItemOrder;
import org.example.rest.dto.DemmandDTO;
import org.example.rest.dto.InformationDemandDTO;
import org.example.rest.dto.InformationItemDemandDTO;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/demands")
public class DemmandController {

    private DemmandService demmandService;

    public DemmandController(DemmandService demmandService) {
        this.demmandService = demmandService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody DemmandDTO dto) {
        Demmand demmand = demmandService.save(dto);
        return demmand.getId();
    }

    @GetMapping("{id}")
    public InformationDemandDTO getById(@PathVariable Integer id) {
        return demmandService.getFullOrder(id)
                .map(p -> convert(p))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Demand not found."));
    }

    private InformationDemandDTO convert(Demmand demmand) {
        return InformationDemandDTO
                .builder()
                .code(demmand.getId())
                .dataOrder(demmand.getDataDemmand().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(demmand.getClient().getCpf())
                .clientName(demmand.getClient().getName())
                .total(demmand.getTotalDemmand())
                .items(convert(demmand.getItemOrders()))
                .build();
    }

    private List<InformationItemDemandDTO> convert(List<ItemOrder> items) {
        if (CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }
        return items.stream().map(itemOrder -> InformationItemDemandDTO
                .builder()
                .productDescription(itemOrder.getProduct().getDescription())
                .unitPrice(itemOrder.getProduct().getUnit_price())
                .quantity(itemOrder.getQuantity())
                .build()).collect(Collectors.toList());
    }
}