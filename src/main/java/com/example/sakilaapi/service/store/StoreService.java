package com.example.sakilaapi.service.store;

import com.example.sakilaapi.dto.ActorDto;
import com.example.sakilaapi.dto.customer.CustomerSummaryDto;
import com.example.sakilaapi.dto.staff.StaffSummaryDto;
import com.example.sakilaapi.dto.store.StoreDto;
import com.example.sakilaapi.dto.store.StoreSummaryDto;
import com.example.sakilaapi.mapper.ActorMapper;
import com.example.sakilaapi.mapper.store.StoreMapper;
import com.example.sakilaapi.model.Actor;
import com.example.sakilaapi.model.Store;
import com.example.sakilaapi.repository.ActorRepository;
import com.example.sakilaapi.repository.StoreRepository;
import com.example.sakilaapi.service.BaseService;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public class StoreService extends BaseService<Store, StoreDto> {
    StoreRepository storeRepository;
    StoreMapper storeMapper;
    public StoreService(StoreRepository storeRepository, StoreMapper storeMapper) {
        super(storeRepository, storeMapper);
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
    }

    @Override
    protected Class<Store> getEntityClass() {
        return Store.class;
    }

    @Override
    protected Class<StoreDto> getDtoClass() {
        return StoreDto.class;
    }

    public List<StoreSummaryDto> getStoreSummaries() {
        List<StoreSummaryDto> summaries = storeRepository.getStoreSummaries();
        return summaries;
    }
    public StoreSummaryDto getStoreSummaryById(Integer id){
        return storeRepository.getStoreSummaryById(id);
    }

    public List<CustomerSummaryDto> getAllCustomersByStoreId(Integer storeId) {
        boolean isStoreExist = storeRepository.isExist(storeId);
        if (!isStoreExist)
            throw new EntityNotFoundException("Can't find store with id: "+storeId);
        List<CustomerSummaryDto> customers = storeRepository.getAllCustomersByStoreId(storeId);
        return customers;
    }

    public StoreDto createStore(StoreSummaryDto storeSummaryDto) {
        return storeRepository.createStore(storeSummaryDto);
    }

    public List<StaffSummaryDto> getAllStaffsByStoreId(Integer storeId) {
        boolean isStoreExist = storeRepository.isExist(storeId);
        if (!isStoreExist)
            throw new EntityNotFoundException("Can't find store with id: "+storeId);
        List<StaffSummaryDto> staffs = storeRepository.getAllStaffsByStoreId(storeId);
        return staffs;
    }
}
