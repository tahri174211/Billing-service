package com.example.Billingservice.client;


import com.example.Billingservice.enitity.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

    @FeignClient(name="customer-service" , url = "http://localhost:8081" , primary = true)
    public interface CustomerServiceClient{
        @GetMapping("/customers/{id}?projection=fullCustomer")
       public  Customer findCustomerById(@PathVariable("id") Long id);
    }