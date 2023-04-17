package com.example.sakilaapi.repository;

import com.example.sakilaapi.model.Staff;

public class StaffRepository extends BaseRepository<Staff,Integer> {
    public StaffRepository() {
        super(Staff.class);
    }
}
