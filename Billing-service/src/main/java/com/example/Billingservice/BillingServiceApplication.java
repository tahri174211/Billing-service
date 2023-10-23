package com.example.Billingservice;

import com.example.Billingservice.client.CustomerServiceClient;
import com.example.Billingservice.client.InventoryServiceClient;
import com.example.Billingservice.enitity.Bill;
import com.example.Billingservice.enitity.Customer;
import com.example.Billingservice.enitity.ProductItem;
import com.example.Billingservice.repository.BillRepository;
import com.example.Billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.context.annotation.Bean;

import java.util.Date;
@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication{
	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
		}

	@Bean
	public FeignContext feignContext() {
		return new FeignContext();
	}
@Bean
public FeignClientProperties feignClientProperties(){ return new FeignClientProperties() ;}
	@Bean
	CommandLineRunner start(BillRepository billRepository, ProductItemRepository
			productItemRepository,
							InventoryServiceClient inventoryServiceClient, CustomerServiceClient customerServiceClient){
		return args->{
				Bill bill=new Bill();
			bill.setBillingDate(new Date()) ;
			Customer customer =customerServiceClient.findCustomerById(1L) ;
			bill.setCustomerID(customer.getId());
			billRepository.save(bill);
			inventoryServiceClient.findAll().getContent().forEach(p->{
			productItemRepository.save(new ProductItem(null,null,p.getId(),p.getPrice(),(int)(1+Math.random()*1000),bill)) ;
				}) ;

	} ;
	}
}



