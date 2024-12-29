package com.jijonj.microservices.order.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shipping_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShippingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}