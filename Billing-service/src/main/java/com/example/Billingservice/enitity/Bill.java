package com.example.Billingservice.enitity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

private Long id; private Date billingDate;
@Transient
@OneToMany(mappedBy = " =bill")
private Collection<ProductItem>
productItems;
@Transient private Customer customer;
private long customerID;
        }
