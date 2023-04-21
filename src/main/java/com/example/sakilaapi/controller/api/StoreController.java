package com.example.sakilaapi.controller.api;

import com.example.sakilaapi.dto.customer.CustomerSummaryDto;
import com.example.sakilaapi.dto.staff.StaffDto;
import com.example.sakilaapi.dto.staff.StaffSummaryDto;
import com.example.sakilaapi.dto.store.StoreDto;
import com.example.sakilaapi.dto.store.StoreSummaryDto;
import com.example.sakilaapi.mapper.store.StoreMapper;
import com.example.sakilaapi.repository.StoreRepository;
import com.example.sakilaapi.service.store.StoreService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/stores")
public class StoreController {
    private final StoreService service = new StoreService(
            new StoreRepository(), StoreMapper.INSTANCE
    );

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStores() {
        List<StoreSummaryDto> summaries = service.getStoreSummaries();
        System.out.println(summaries.stream().limit(3));
        GenericEntity entity = new GenericEntity<>(summaries) {
        };
        return Response.ok(entity).build();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStoreBy(@PathParam("id") Integer id) {
        Optional<StoreSummaryDto> optionalStore = Optional.ofNullable(service.getStoreSummaryById(id));
        return Response.ok().entity(
                optionalStore.get()
        ).build();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStore(StoreSummaryDto storeSummaryDto) {
        Optional<StoreDto> optionalStoreDto = Optional.ofNullable(service.createStore(storeSummaryDto));
        if (optionalStoreDto.isPresent()){
            return Response.ok(optionalStoreDto.get()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Can't create store").build();
    }

    @GET
    @Path("/{storeId}/customers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomers(@PathParam("storeId") Integer storeId){
       List<CustomerSummaryDto> customers = service.getAllCustomersByStoreId(storeId);
       return Response.ok(customers).build();
    }

    @GET
    @Path("/{storeId}/staffs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStaff(@PathParam("storeId") Integer storeId){
        List<StaffSummaryDto> staffs = service.getAllStaffsByStoreId(storeId);
        return Response.ok(staffs).build();
    }

}