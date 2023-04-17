package com.example.sakilaapi.repository;

import com.example.sakilaapi.model.Inventory;

public class InventoryRepository extends BaseRepository<Inventory,Integer> {

    public InventoryRepository() {
        super(Inventory.class);
    }
}
