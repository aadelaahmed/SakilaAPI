package com.example.sakilaapi.repository;

import com.example.sakilaapi.dto.staff.StaffSummaryDto;
import com.example.sakilaapi.mapper.staff.StaffSummaryMapper;
import com.example.sakilaapi.model.Staff;
import com.example.sakilaapi.util.Database;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class StaffRepository extends BaseRepository<Staff,Integer> {
    StaffSummaryMapper staffSummaryMapper;
    public StaffRepository() {
        super(Staff.class);
        this.staffSummaryMapper = StaffSummaryMapper.INSTANCE;
    }

    public List<StaffSummaryDto> getStaffSummaries() {
        return Database.doInTransaction(
                entityManager -> {
                    List<Staff> stores = getAll(entityManager);
                    return staffSummaryMapper.toDto(stores);
                }
        );
    }

    public StaffSummaryDto getStoreSummaryById(Integer id) {
        return Database.doInTransaction(
                entityManager -> {
                    Staff staff = entityManager.find(Staff.class, id);
                    if (staff == null)
                        throw new EntityNotFoundException("Staff can't be fetched with id: "+id);
                    return staffSummaryMapper.toDto(staff);
                }
        );
    }
}
