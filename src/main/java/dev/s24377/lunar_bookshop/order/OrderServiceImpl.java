package dev.s24377.lunar_bookshop.order;

import dev.s24377.lunar_bookshop.book.*;
import dev.s24377.lunar_bookshop.enums.ORDER_TYPE;
import dev.s24377.lunar_bookshop.order_book.OrderBook;
import dev.s24377.lunar_bookshop.order_book.OrderBookRepository;
import dev.s24377.lunar_bookshop.supplier.Supplier;
import dev.s24377.lunar_bookshop.supplier.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {

    private final SupplierRepository supplierRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;
    private final OrderBookRepository orderBookRepository;

    @Override
    public Order placeOrder(NewOrderDTO newOrderDTO) {
        Supplier supplier = supplierRepository.getById(newOrderDTO.getSupplierId());

        Order order = Order.builder()
                .orderedDate(LocalDate.now())
                .supplier(supplier)
                .type(ORDER_TYPE.PENDING)
                .build();

        orderRepository.save(order);

        List<OrderBook> orderBooks = new ArrayList<>();
        for(var isbn : newOrderDTO.getBooksMap().keySet()){
            OrderBook orderBook = OrderBook.builder()
                    .order(order)
                    .book(bookRepository.findByIsbn(isbn).get())
                    .quantity(newOrderDTO.getBooksMap().get(isbn))
                    .build();
            orderBooks.add(orderBook);
            orderBookRepository.save(orderBook);
        }

        order.setOrderBooks(orderBooks);

        orderRepository.save(order);




        return order;
    }
}
