package com.example.sakilaapi.controller.api;

import com.example.sakilaapi.dto.rental.RentalDto;
import com.example.sakilaapi.dto.customer.CustomerDto;
import com.example.sakilaapi.dto.PaymentDto;
import com.example.sakilaapi.dto.customer.CustomerSummaryDto;
import com.example.sakilaapi.dto.rental.RentalSummaryDto;
import com.example.sakilaapi.dto.staff.StaffDto;
import com.example.sakilaapi.dto.staff.StaffSummaryDto;
import com.example.sakilaapi.mapper.customer.CustomerMapper;
import com.example.sakilaapi.repository.CustomerRepository;
import com.example.sakilaapi.service.customer.CustomerService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Path("/customers")
public class CustomerController {
    //TODO -> USE IOC spring container HERE

    private final CustomerService customerService = new CustomerService(
            new CustomerRepository(),
            CustomerMapper.INSTANCE
    );


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomers() {
        List<CustomerSummaryDto> customers = customerService.getAllCustomers();
        GenericEntity<List<CustomerSummaryDto>> entity = new GenericEntity<>(customers) {
        };
        return Response.ok(entity).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("id") Integer id, CustomerDto customerDto) {
        customerDto.setId(id);
        CustomerDto res = customerService.update(id, customerDto);
        if (res != null) {
            return Response.ok(res).build();
        } else
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("can't update this Staff").build();
    }

    @GET
    @Path("/{customerId}/rentals")
    public Response getRentalsByCustomerId(@PathParam("customerId") Integer customerId){
        List<RentalSummaryDto> rentalDtos = customerService.getRentalsByCustomerId(customerId);
        return Response.ok(rentalDtos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("id") int id) {
        CustomerSummaryDto customerSummaryDto = customerService.getCustomerSummariesById(id);
        if (customerSummaryDto != null) {
            return Response.ok(customerSummaryDto).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Customer with id " + id + " not found").build();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(CustomerDto customerDto) {
        customerDto.setLastUpdate(Instant.now());
        customerDto.setCreateDate(Instant.now());
        Optional<CustomerSummaryDto> optionalCustomerSummaryDto = Optional.ofNullable(customerService.createCustomerByEmail(customerDto));
        if (optionalCustomerSummaryDto.isPresent()){
            return Response.ok(optionalCustomerSummaryDto.get()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Can't create customer member").build();
    }
    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Integer id) {
        customerService.deleteById(id);
        return Response.ok("Customer was deleted successfully").build();
    }

   /* @GET
    @Path("/{id}/payments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPaymentsByCustomerId(@PathParam("id") Integer id) {
        //TODO -> check this endpoint
        List<PaymentDto> paymentDtos = customerService.getPaymentsByCustomerId(id);
        return Response.ok(paymentDtos).build();
    }*/
}
