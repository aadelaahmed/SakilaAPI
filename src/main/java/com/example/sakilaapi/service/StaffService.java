package com.example.sakilaapi.service;

import com.example.sakilaapi.dto.staff.StaffDto;
import com.example.sakilaapi.dto.staff.StaffSummaryDto;
import com.example.sakilaapi.dto.store.StoreDto;
import com.example.sakilaapi.dto.store.StoreSummaryDto;
import com.example.sakilaapi.mapper.BaseMapper;
import com.example.sakilaapi.mapper.staff.StaffMapper;
import com.example.sakilaapi.mapper.store.StoreMapper;
import com.example.sakilaapi.model.Staff;
import com.example.sakilaapi.model.Store;
import com.example.sakilaapi.repository.BaseRepository;
import com.example.sakilaapi.repository.StaffRepository;
import com.example.sakilaapi.repository.StoreRepository;

import java.util.List;

public class StaffService extends BaseService<Staff, StaffDto> {
    StaffRepository staffRepository;
    StaffMapper staffMapper;
    public StaffService(StaffRepository staffRepository, StaffMapper staffMapper) {
        super(staffRepository, staffMapper);
        this.staffRepository = staffRepository;
        this.staffMapper = staffMapper;
    }

    @Override
    protected Class<Staff> getEntityClass() {
        return Staff.class;
    }

    @Override
    protected Class<StaffDto> getDtoClass() {
        return StaffDto.class;
    }


    public StaffSummaryDto getStoreSummaryById(Integer id){
        return staffRepository.getStoreSummaryById(id);
    }
    public List<StaffSummaryDto> getStaffSummaries() {
        return staffRepository.getStaffSummaries();
    }
}
