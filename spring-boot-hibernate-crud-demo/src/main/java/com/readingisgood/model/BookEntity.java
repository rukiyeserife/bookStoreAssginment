package com.readingisgood.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="TBL_BOOK")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="bookName")
    private String bookName;

    @Column(name="bookVendor")
    private String bookVendor;

    @Column(name="bookDescription")
    private String bookDescription;

    @Column(name="quantityInStock")
    private Long  quantityInStock;

    @Column(name="buyPrice")
    private double buyPrice;

    @ManyToMany(mappedBy = "bookEntities")
    private Set<OrderEntity> orderEntities = new HashSet<>();
}
