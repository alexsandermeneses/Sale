package org.example.Service.impl;

import lombok.RequiredArgsConstructor;
import org.example.Service.DemmandService;
import org.example.domain.entity.Client;
import org.example.domain.entity.Demmand;
import org.example.domain.entity.ItemOrder;
import org.example.domain.entity.Product;
import org.example.domain.repository.ClientRepository;
import org.example.domain.repository.DemmandRepository;
import org.example.domain.repository.ItemOrderRepository;
import org.example.domain.repository.ProductRepository;
import org.example.exceptions.RuleBusinessException;
import org.example.rest.dto.DemmandDTO;
import org.example.rest.dto.ItemsOrderDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // é gerado um construtor para os "finals"
public class DemmandServiceImpl implements DemmandService {

    private final DemmandRepository demmandRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final ItemOrderRepository itemOrderRepository;

    @Override
    @Transactional
    public Demmand save(DemmandDTO demmandDTO) {
        Integer idClient = demmandDTO.getClient();
        Client client = clientRepository
                .findById(idClient)
                .orElseThrow(() -> new RuleBusinessException("Client code invalid"));

        Demmand demmand = new Demmand();
        demmand.setTotalDemmand(demmandDTO.getTotal());
        demmand.setDataDemmand(LocalDate.now());
        demmand.setClient(client);

        List<ItemOrder> itemOrders = convertItems(demmand, demmandDTO.getItemsOrderDTOS());
        demmandRepository.save(demmand);
        itemOrderRepository.saveAll(itemOrders);
        demmand.setItemOrders(itemOrders);
        return demmand;
    }

    @Override
    public Optional<Demmand> getFullOrder(Integer id) {
        return demmandRepository.findByIdFetchItemOrders(id);
    }

    private List<ItemOrder> convertItems(Demmand demmand, List<ItemsOrderDTO> items){
        if (items == null || items.isEmpty()) {
            throw new RuleBusinessException("It's not possible to place a demand without an item");
        }

        return items
                .stream()
                .map( demandDTO -> {
            Integer idProduct = demandDTO.getProduct();
            Product product = productRepository
                    .findById(idProduct)
                    .orElseThrow(
                            () -> new RuleBusinessException(
                                    "Product code invalid: " + idProduct
                            ));

            ItemOrder itemOrder = new ItemOrder();
            itemOrder.setQuantity(demandDTO.getQuantity());
            itemOrder.setDemmand(demmand);
            itemOrder.setProduct(product);
            return itemOrder;
        }).collect(Collectors.toList());
    }
}