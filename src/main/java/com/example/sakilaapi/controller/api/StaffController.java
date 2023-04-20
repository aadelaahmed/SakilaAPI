package com.example.sakilaapi.controller.api;

import com.example.sakilaapi.dto.staff.StaffDto;
import com.example.sakilaapi.dto.staff.StaffSummaryDto;
import com.example.sakilaapi.mapper.staff.StaffMapper;
import com.example.sakilaapi.repository.StaffRepository;
import com.example.sakilaapi.service.staff.StaffService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/staff")
public class StaffController {
    //TODO -> USE IOC spring container HERE
    private final StaffService service = new StaffService(
            new StaffRepository(), StaffMapper.INSTANCE
    );

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<StaffSummaryDto> staffs = service.getStaffSummaries();
        System.out.println(staffs.stream().limit(3));
        GenericEntity entity = new GenericEntity<>(staffs) {
        };
        return Response.ok(entity).build();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id) {
        Optional<StaffSummaryDto> optionalStaff = Optional.ofNullable(service.getStoreSummaryById(id));
        return Response.ok().entity(
                optionalStaff.get()
        ).build();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(StaffDto staffDto) {
        Optional<StaffDto> optionalStaffDto = Optional.ofNullable(service.create(staffDto,staffDto.getId()));
        if (optionalStaffDto.isPresent()){
            return Response.ok(optionalStaffDto.get()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Can't create staff member").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, StaffDto staffDto) {
        staffDto.setId(id);
        StaffDto res = service.update(id, staffDto);
        if (res != null) {
            return Response.ok(res).build();
        } else
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("can't update this Staff").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Integer id) {
        service.deleteById(id);
        return Response.ok("Staff was deleted successfully").build();
    }


}
