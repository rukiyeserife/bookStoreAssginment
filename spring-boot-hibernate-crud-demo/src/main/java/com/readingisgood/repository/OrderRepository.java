package com.readingisgood.repository;

import com.readingisgood.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository
        extends JpaRepository<OrderEntity, Long> {
    public List<OrderEntity> findAllByOrderDateLessThanEqualAndShippedDateGreaterThanEqual(Date orderDate, Date shippedDate);

    public long countById(Long id);

}
