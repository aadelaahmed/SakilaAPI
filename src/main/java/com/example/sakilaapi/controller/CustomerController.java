/*
package com.example.sakilaapi.controller;

@Path("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomers() {
        List<CustomerDto> customers = customerService.getAllCustomers();
        GenericEntity<List<CustomerDto>> entity = new GenericEntity<>(customers) {};
        return Response.ok(entity).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("id") int id) {
        CustomerDto customerDto = customerService.getCustomerById(id);
        if (customerDto != null) {
            return Response.ok(customerDto).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Customer with id " + id + " not found").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(CustomerDto customerDto) {
        CustomerDto createdCustomerDto
    }*/
