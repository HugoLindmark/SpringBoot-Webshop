package com.g09.webshopspringboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {

    private Role role;
    private String userName;
    private String firstName;
    private String lastName;
    @JsonIgnore
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<OrderPurchase> orders = new ArrayList<>();

    public User(String userName, String firstName, String lastName, String password, Role role) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }


    @JsonProperty("totalSpent")
    public String getFormattedTotalSpent() {
        int sum = getTotalSpent();
        return FormatNumber.formatCurrency(sum);
    }

    @JsonIgnore
    public int getTotalSpent() {
        return orders.parallelStream().mapToInt(OrderPurchase::getOrderTotal).sum();
    }

    public List<OrderPurchase> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderPurchase> orders) {
        this.orders = orders;
    }
}