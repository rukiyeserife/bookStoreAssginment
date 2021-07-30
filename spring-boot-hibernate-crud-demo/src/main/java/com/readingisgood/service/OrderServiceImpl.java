package com.readingisgood.service;

import com.readingisgood.dto.OrderCreateRequest;
import com.readingisgood.dto.StatisticOrderDTO;
import com.readingisgood.exception.OutOfStockException;
import com.readingisgood.exception.RecordNotFoundException;
import com.readingisgood.model.BookEntity;
import com.readingisgood.model.OrderEntity;
import com.readingisgood.repository.BookRepository;
import com.readingisgood.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class OrderServiceImpl implements OrderService {

    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;


    @Override
    public OrderEntity createOrder(OrderCreateRequest orderCreateRequest) throws EntityExistsException, OutOfStockException {
        //TODO
        // valid mi ?
        return checkOrderStatus(orderCreateRequest);
    }

    @Transactional
    private OrderEntity checkOrderStatus(OrderCreateRequest orderCreateRequest) throws OutOfStockException {
        Map<Long, Long> bookEntityIntegerMap = orderCreateRequest.getBookOrders();
        for (Map.Entry<Long, Long> entry : bookEntityIntegerMap.entrySet()) {
            Optional<BookEntity> bookEntity = bookRepository.findById(entry.getKey());
            if (!bookEntity.isPresent()) {
                throw new OutOfStockException("Out of stock!");
            }
            Long quantityInStock = bookEntity.get().getQuantityInStock();
            if (quantityInStock > entry.getValue()) {
                //            writeLock.lock();
                try {
                    bookEntity.get().setQuantityInStock(quantityInStock - entry.getValue());
                    bookRepository.save(bookEntity.get());
                } finally {
                    //              writeLock.unlock();
                }

            } else {
                throw new OutOfStockException("Out of stock!");
            }
        }
        OrderEntity newEntity = OrderEntity.builder().status(Boolean.TRUE).
                orderDate(orderCreateRequest.getOrderDate()).shippedDate(orderCreateRequest.getShippedDate()).build();
        return orderRepository.save(newEntity);


//        //  readLock.lock();
//        try {
//            List<BookEntity> bookEntities = orderEntity.getBookEntities();
//            for (BookEntity bookEntity : bookEntities) {
//                int count = orderBookRepository.findByOrderIdAndBookId(orderId, bookEntity.getId());
//                int quantityInStock = bookEntity.getQuantityInStock();
//                if (quantityInStock > count) {
//                    //            writeLock.lock();
//                    try {
//                        bookEntity.setQuantityInStock(quantityInStock - count);
//                        bookRepository.save(bookEntity);
//                    } finally {
//                        //              writeLock.unlock();
//                    }
//                } else {
//                    throw new OutOfStockException("Out of stock!");
//                }
//            }
//
//        } finally {
//            //readLock.unlock();
//        }

    }

    @Override
    public OrderEntity getOrderById(Long id) throws RecordNotFoundException {
        Optional<OrderEntity> order = orderRepository.findById(id);

        if (order.isPresent()) {
            return order.get();
        } else {
            throw new RecordNotFoundException("No order record exist for given id");
        }
    }

    @Override
    public List<OrderEntity> findAllByOrderDateLessThanEqualAndShippedDateGreaterThanEqual(Date orderDate, Date shippedDate) {

        return orderRepository.findAllByOrderDateLessThanEqualAndShippedDateGreaterThanEqual(orderDate, shippedDate);
    }

    @Override
    public long countById(Long id) {
        return orderRepository.countById(id);
    }

    @Override
    public List<StatisticOrderDTO> getStatisticDTOList() {
        return null;
    }


}
