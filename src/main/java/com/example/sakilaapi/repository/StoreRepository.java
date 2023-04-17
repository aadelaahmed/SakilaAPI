package com.example.sakilaapi.repository;

import com.example.sakilaapi.dto.customer.CustomerSummaryDto;
import com.example.sakilaapi.dto.store.StoreSummaryDto;
import com.example.sakilaapi.mapper.customer.CustomerSummaryMapper;
import com.example.sakilaapi.mapper.store.StoreSummaryMapper;
import com.example.sakilaapi.model.Actor;
import com.example.sakilaapi.model.Customer;
import com.example.sakilaapi.model.Store;
import com.example.sakilaapi.util.Database;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

public class StoreRepository extends BaseRepository<Store, Integer> {
    StoreSummaryMapper storeSummaryMapper;
    public StoreRepository() {
        super(Store.class);
        this.storeSummaryMapper = StoreSummaryMapper.INSTANCE;
    }


    public List<StoreSummaryDto> getStoreSummaries() {
        return Database.doInTransaction(
                entityManager -> {
                    List<Store> stores = getAll(entityManager);

                    return storeSummaryMapper.toDto(stores);
                }
        );
    }

    public StoreSummaryDto getStoreSummaryById(Integer id){
        return Database.doInTransaction(
                entityManager -> {
                    Store store = entityManager.find(Store.class, id);
                    return storeSummaryMapper.toDto(store);
                }
        );
    }
}