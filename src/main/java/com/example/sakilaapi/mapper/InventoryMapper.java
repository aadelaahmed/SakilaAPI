package com.example.sakilaapi.mapper;

import com.example.sakilaapi.dto.InventoryDto;
import com.example.sakilaapi.mapper.BaseMapper;
import com.example.sakilaapi.mapper.FilmMapper;
import com.example.sakilaapi.mapper.StoreMapper;
import com.example.sakilaapi.model.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI, uses = {FilmMapper.class, StoreMapper.class})
public interface InventoryMapper extends BaseMapper<InventoryDto, Inventory> {
}