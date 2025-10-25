package com.shopverse.inventoryservice.controller;

import com.shopverse.inventoryservice.dto.InventoryRequest;
import com.shopverse.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("sku-code") String skuCode){
        return inventoryService.isInStock(skuCode);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addInventory(@RequestBody InventoryRequest inventoryRequest){
        inventoryService.addInventory(inventoryRequest);
        return "Inventory added successfully";
    }

}
