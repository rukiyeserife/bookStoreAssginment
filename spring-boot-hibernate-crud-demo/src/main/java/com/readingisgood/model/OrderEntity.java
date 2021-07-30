package com.readingisgood.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="TBL_ORDER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="orderDate")
    private Date orderDate;

    @Column(name="shippedDate")
    private Date shippedDate;

    @Column(name="status")
    private boolean status;

    @ManyToMany(targetEntity = BookEntity.class, cascade = { CascadeType.ALL })
    @JoinTable(name = "TBL_ORDER_BOOK",
            joinColumns = { @JoinColumn(name = "orderIdFK" ) },
            inverseJoinColumns = { @JoinColumn(name = "bookIdFK") })
    private Set<BookEntity> bookEntities = new HashSet<>();


    @Column(name="quantityInOrdered")
    private int quantityInOrdered;
}