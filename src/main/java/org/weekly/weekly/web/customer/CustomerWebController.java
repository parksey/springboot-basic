package org.weekly.weekly.web.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.weekly.weekly.customer.dto.request.CustomerCreationRequest;
import org.weekly.weekly.customer.dto.response.CustomerResponse;
import org.weekly.weekly.customer.dto.response.CustomersResponse;
import org.weekly.weekly.customer.exception.CustomerException;
import org.weekly.weekly.customer.service.CustomerService;
import org.weekly.weekly.web.exception.WebExceptionDto;

@Controller
@RequestMapping("/customer")
public class CustomerWebController  {
    private final Logger logger = LoggerFactory.getLogger(CustomerWebController.class);
    private final CustomerService customerService;

    public CustomerWebController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/findCustomers")
    public String findCustomers(Model model) {
        CustomersResponse customersResponse = customerService.findAllCustomer();
        model.addAttribute("customers", customersResponse);
        return "customer/findCustomers";
    }

    @GetMapping("/create")
    public String createCustomer() {
        return "customer/createCustomer";
    }

    @PostMapping("/create")
    public String createCustomer(CustomerCreationRequest creationRequest, Model model) {
        try {
            CustomerResponse customerResponse = customerService.createCustomer(creationRequest);
            model.addAttribute("customer", customerResponse);
        } catch (CustomerException exception) {
            model.addAttribute("exception", new WebExceptionDto(exception));
            return "exception/exception";
        }
        return "redirect:";
    }
}
