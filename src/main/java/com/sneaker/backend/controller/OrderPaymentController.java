package com.sneaker.backend.controller;

import com.sneaker.backend.dto.PlacedOrderDto;
import com.sneaker.backend.dto.OrderHistoryDto;
import com.sneaker.backend.dto.PaymentDto;
import com.sneaker.backend.dto.PlacingOrderDto;
import com.sneaker.backend.service.OrderPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderPaymentController {

    @Autowired
    private OrderPaymentService orderPaymentService;

    // TODO: ENTITIES SHOULD BE EASY TO UNDERSTAND
    // TODO: @Transcational i think not working
    // TODO: DB QUERIES SHOULD BE LESS

    @PostMapping("/order/place")
    public ResponseEntity<PlacedOrderDto> placeOrder(@RequestBody PlacingOrderDto placingOrderDto) {
        try {
            PlacedOrderDto placedOrderDto = orderPaymentService.placeOrder(placingOrderDto);
            return ResponseEntity.ok(placedOrderDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/order/myOrder/{orderId}")
    public ResponseEntity<OrderHistoryDto> getOrderHistory(@PathVariable Long orderId){
        try {
            OrderHistoryDto orderHistoryDto = orderPaymentService.getOrderHistory(orderId);
            return ResponseEntity.ok(orderHistoryDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/order/myOrder")
    public ResponseEntity<List<OrderHistoryDto>> getMyOrder(){
        try {
            List<OrderHistoryDto> orderHistoryDto = orderPaymentService.getMyOrders();
            return ResponseEntity.ok(orderHistoryDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }


    @DeleteMapping("/order/cancel/{id}")
    public ResponseEntity<PlacedOrderDto> refundOrder(Long orderId){
        try {
            PlacedOrderDto status = orderPaymentService.refundOrder(orderId);
            return ResponseEntity.ok(status);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
