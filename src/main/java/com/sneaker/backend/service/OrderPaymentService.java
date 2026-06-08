package com.sneaker.backend.service;

import com.sneaker.backend.config.Helper;
import com.sneaker.backend.dto.*;
import com.sneaker.backend.entities.*;
import com.sneaker.backend.entities.role.OrderStatus;
import com.sneaker.backend.entities.role.PaymentMode;
import com.sneaker.backend.entities.role.PaymentStatus;
import com.sneaker.backend.repository.*;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderPaymentService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private Helper helper;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;

    @Transactional
    public PlacedOrderDto placeOrder(PlacingOrderDto placingOrderDto){
        // find user cart
        Long userId = helper.getCurrentUserId();

        List<CartEntity> cartItems  = cartRepo.findByUserId(userId);

        OrdersEntity ordersEntity;


        if(cartItems == null){
            throw new RuntimeException("No Order to Placed");
        }
        else{
            // create order
            Double total_amount = helper.calculateTotalAmount(cartItems);


            ordersEntity = OrdersEntity.builder()
                    .address(placingOrderDto.getAddress())
                    .user(helper.getCurrentUser())
                    .createdAt(LocalDateTime.now())
                    .orderStatus(OrderStatus.PENDING)
                    .totalAmount(total_amount)
                    .paymentMode(placingOrderDto.getPaymentMode())
                    .build();

            List<OrderItemsEntity> orderItems = cartItems.stream()
                    .map(cart -> OrderItemsEntity.builder()
                            .product(cart.getProduct())
                            .quantity(cart.getQuantity())
                            .size(cart.getSize())
                            .order(ordersEntity)
                            .build())
                    .toList();

            ordersEntity.setOrderItems(orderItems);


            // TODO : CASH ME HONE KE BAD BHI CALL KRKE FAIL ARA H
            PaymentStatus paymentStatus = doPayment(total_amount,
                    placingOrderDto.getPaymentMode(), ordersEntity.getId());


               if(paymentStatus == PaymentStatus.COMPLETED || placingOrderDto.getPaymentMode() == PaymentMode.CASH) {
                   // update Order
                   ordersEntity.setOrderStatus(OrderStatus.CONFIRMED);
                   String delivery = calculateDeliveryDate(LocalDate.now());
                   ordersEntity.setDeliveryDate(delivery);

                   // reduce the qty
                   // this loop will execute once for each cart item.

                   cartItems.forEach(cart -> {
                       ProductEntity product = cart.getProduct();
                       String size = String.valueOf(cart.getSize());
                       Map<String, Integer> sizeStock = product.getSizeStock();

                       int currentStock = sizeStock.getOrDefault(size,0);

                       sizeStock.put(size,currentStock - cart.getQuantity());

                       product.setSizeStock(sizeStock);
                       productRepo.save(product);
                   });

                   // delete items from cart
                   cartRepo.deleteAll(cartItems);
               }
               else if(paymentStatus == PaymentStatus.FAILED || paymentStatus == PaymentStatus.INSUFFICIENT_WALLET_BALANCE){
                   ordersEntity.setOrderStatus(OrderStatus.CANCELLED);
                   ordersEntity.setDeliveryDate("Not Applicable");
               }

            orderRepo.save(ordersEntity);
            }

        return mapper.map(ordersEntity, PlacedOrderDto.class);
    }

    public PaymentStatus doPayment(Double amount, PaymentMode paymentMode, Long orderId){
        switch (paymentMode){
            case UPI:
                // call UPI payment service
                return callExternalUpiGateway(orderId, amount);
            case CREDIT_CARD:
                // call CREDIT CARD service
                return callExternalCardGateway(orderId, amount);
            case WALLET:
                UserEntity currentUser = helper.getCurrentUser();
                if(currentUser.getUserWallet() >= amount){
                    double deductedAmount = currentUser.getUserWallet() - amount;
                    currentUser.setUserWallet(deductedAmount);
                    userRepo.save(currentUser);
                    return PaymentStatus.COMPLETED;
                }
                else {
                    return PaymentStatus.INSUFFICIENT_WALLET_BALANCE;
                }
            default:
                return PaymentStatus.FAILED;
        }
    }

    public PlacedOrderDto refundOrder(Long orderId){
        return new PlacedOrderDto();
    }

    public OrderHistoryDto getOrderHistory(Long orderId){
        OrdersEntity ordersEntity = orderRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        return mapper.map(ordersEntity,OrderHistoryDto.class);
    }

    public List<OrderHistoryDto> getMyOrders(){
        List<OrdersEntity> ordersEntity = orderRepo.findByUserId(helper.getCurrentUserId());


        return ordersEntity.stream()
                .map(getUserOrderList -> mapper.map(getUserOrderList,OrderHistoryDto.class))
                .toList();
    }

    private PaymentStatus callExternalUpiGateway(Long orderId, Double amount) {
        // Simulate calling Razorpay, PhonePe, or Stripe API
        System.out.println("Initiating UPI Payment for Order: " + orderId);
        return PaymentStatus.COMPLETED;
    }

    private PaymentStatus callExternalCardGateway(Long orderId, Double amount) {
        // Simulate calling a Card processing merchant API
        System.out.println("Initiating Credit Card Payment for Order: " + orderId);
        return PaymentStatus.COMPLETED;
    }

    public String calculateDeliveryDate(LocalDate orderCreatedDate) {
        return orderCreatedDate.plusDays(3).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
