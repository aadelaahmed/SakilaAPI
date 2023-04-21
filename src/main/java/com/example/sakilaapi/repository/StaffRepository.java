package com.example.sakilaapi.repository;

import com.example.sakilaapi.dto.staff.StaffDto;
import com.example.sakilaapi.dto.staff.StaffSummaryDto;
import com.example.sakilaapi.exception.EntityAlreadyExistException;
import com.example.sakilaapi.mapper.staff.StaffMapper;
import com.example.sakilaapi.mapper.staff.StaffSummaryMapper;
import com.example.sakilaapi.model.Address;
import com.example.sakilaapi.model.Staff;
import com.example.sakilaapi.model.Store;
import com.example.sakilaapi.util.Database;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StaffRepository extends BaseRepository<Staff,Integer> {
    StaffSummaryMapper staffSummaryMapper;
    StaffMapper staffMapper;
    public StaffRepository() {
        super(Staff.class);
        this.staffSummaryMapper = StaffSummaryMapper.INSTANCE;
        this.staffMapper = StaffMapper.INSTANCE;
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

    public StaffSummaryDto createStaffByEmail(StaffDto staffDto) {
        return Database.doInTransaction(
                entityManager -> {
                    if (staffDto.getEmail() != null && staffDto != null) {
                        Optional<Staff> optionalEntity = getByName("email",staffDto.getEmail());
                        if (optionalEntity.isPresent()) {
                            throw new EntityAlreadyExistException("Staff is already existed");
                        }
                    }else
                        throw new EntityNotFoundException("Can't create this staff");
                    Staff staff = staffMapper.toEntity(staffDto);
                    //get store first and set it to new created staff
                    Store store = entityManager.find(Store.class,staffDto.getStoreId());
                    if (store==null)
                        throw new EntityNotFoundException("Can't find store with id: "+staffDto.getStoreId());
                    staff.setStore(store);
                    staff = save(staff);
                    return staffSummaryMapper.toDto(staff);
                }
        );
    }

    public Staff getStaffManagerByName(String managerFirstName, String managerLastName) {
        return Database.doInTransaction(
                entityManager -> {
                    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                    CriteriaQuery<Staff> query = criteriaBuilder.createQuery(Staff.class);
                    Root<Staff> root = query.from(Staff.class);
                    List<Predicate> predicates = new ArrayList<>();
                    if (managerFirstName != null) {
                        predicates.add(criteriaBuilder.equal(root.get("firstName"), managerFirstName));
                    }
                    if (managerLastName != null) {
                        predicates.add(criteriaBuilder.equal(root.get("lastName"), managerLastName));
                    }
                    query.select(root).distinct(true).where(predicates.toArray(new Predicate[0]));
                    TypedQuery<Staff> typedQuery = entityManager.createQuery(query);
                    return typedQuery.getResultList().get(0);
                }
        );
    }
}
