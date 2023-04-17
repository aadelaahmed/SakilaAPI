package com.example.sakilaapi.controller.api;

import com.example.sakilaapi.dto.customer.CustomerDto;
import com.example.sakilaapi.dto.PaymentDto;
import com.example.sakilaapi.dto.customer.CustomerSummaryDto;
import com.example.sakilaapi.mapper.customer.CustomerMapper;
import com.example.sakilaapi.repository.CustomerRepository;
import com.example.sakilaapi.service.customer.CustomerServiceImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/customers")
public class CustomerController {
    //TODO -> USE IOC spring container HERE

    private final CustomerServiceImpl customerService = new CustomerServiceImpl(
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

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("id") int id) {
        CustomerDto customerDto = customerService.getById(id);
        if (customerDto != null) {
            return Response.ok(customerDto).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Customer with id " + id + " not found").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(CustomerDto customerDto) {
        Optional<CustomerDto> optionalCustomerDto = Optional.ofNullable(customerService.create(customerDto, customerDto.getId()));
        if (optionalCustomerDto.isPresent()) {
            return Response.ok(optionalCustomerDto.get()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Can't create customer").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, CustomerDto customerDto) {
        customerDto.setId(id);
        Optional<CustomerDto> optionalCustomerDto = Optional.of(customerService.update(id,customerDto));
        if (optionalCustomerDto.isPresent()){
            return Response.ok(optionalCustomerDto.get()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("can't update this customer").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Integer id) {
        customerService.deleteById(id);
        return Response.ok("Customer was deleted successfully").build();
    }
    @GET
    @Path("/{id}/payments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPaymentsByCustomerId(@PathParam("id") Integer id){
        //TODO -> check this endpoint
        List<PaymentDto> paymentDtos = customerService.getPaymentsByCustomerId(id);
        return Response.ok(paymentDtos).build();
    }
}
