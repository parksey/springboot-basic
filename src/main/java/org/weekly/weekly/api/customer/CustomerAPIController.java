package org.weekly.weekly.api.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.weekly.weekly.customer.dto.request.CustomerCreationRequest;
import org.weekly.weekly.customer.dto.request.CustomerUpdateRequest;
import org.weekly.weekly.customer.dto.response.CustomerResponse;
import org.weekly.weekly.customer.dto.response.CustomersResponse;
import org.weekly.weekly.customer.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerAPIController {

    private final CustomerService customerService;

    public CustomerAPIController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer")
    public ResponseEntity<CustomerResponse> create(@RequestBody CustomerCreationRequest creationRequest) {
        CustomerResponse customerResponse = customerService.createCustomer(creationRequest);
        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerResponse>> customers() {
        CustomersResponse customersResponse = customerService.findAllCustomer();
        return new ResponseEntity<>(customersResponse.getCustomerResponses(), HttpStatus.OK);
    }

    @GetMapping("/find/{customerEmail}")
    public ResponseEntity<CustomerResponse> findCustomer(@PathVariable String customerEmail) {
        CustomerResponse customerResponse = customerService.findDetailCustomer(customerEmail);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody CustomerUpdateRequest customerUpdateRequest) {
        CustomerResponse customerResponse = customerService.updateCustomer(customerUpdateRequest);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{customerEmail}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String customerEmail) {
        customerService.deleteCustomer(customerEmail);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<Void> deleteAll() {
        customerService.deleteAllCustomers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
