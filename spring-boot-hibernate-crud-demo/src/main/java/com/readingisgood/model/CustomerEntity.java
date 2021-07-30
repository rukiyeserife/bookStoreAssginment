package com.readingisgood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="TBL_CUSTOMER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="first_name")
    private String firstName;
    
    @Column(name="last_name")
    private String lastName;
    
    @Column(name="email", nullable=false, length=200)
    private String email;

    @Column(name="adress", nullable=false, length=200)
    private String adress;

    @Column(name="phone", nullable=false, length=200)
    private String phone;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="customerNoFK", nullable = false, updatable = false)
    @JsonIgnore
    private Set<OrderEntity> orders;

}