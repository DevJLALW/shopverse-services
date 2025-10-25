package com.shopverse.inventoryservice.service;

import com.shopverse.inventoryservice.dto.InventoryRequest;
import com.shopverse.inventoryservice.dto.InventoryResponse;
import com.shopverse.inventoryservice.model.Inventory;
import com.shopverse.inventoryservice.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode){
        return inventoryRepository.findBySkuCodeIn(skuCode)
                .stream()
                .map( inventory ->
                        InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() >0)
                                .build()).toList();

    }

    @Transactional
    public void addInventory(InventoryRequest inventoryRequest){

        List<Inventory> inventoryList = inventoryRequest.getInventoryItemsDtoList()
                .stream().map(dto -> {
                    Inventory inventory = new Inventory();
                    inventory.setSkuCode(dto.getSkuCode());
                    inventory.setQuantity(dto.getQuantity());
                    return inventory;
                }).toList();
        inventoryRepository.saveAll(inventoryList);

    }

}
