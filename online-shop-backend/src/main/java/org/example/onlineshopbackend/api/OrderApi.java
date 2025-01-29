package org.example.onlineshopbackend.api;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopbackend.api.input.OrderRequest;
import org.example.onlineshopbackend.api.output.CategoryInfo;
import org.example.onlineshopbackend.api.output.MessageResponse;
import org.example.onlineshopbackend.api.output.OrderInfo;
import org.example.onlineshopbackend.model.entity.OrderItem.OrderStatus;
import org.example.onlineshopbackend.model.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderApi {

    private final OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<MessageResponse> createOrder(@RequestBody OrderRequest orderRequest, BindingResult result) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderRequest));
    }

    @GetMapping("/itemId/{orderItemId}/state/{orderStatus}")
    public ResponseEntity<MessageResponse> updateOrderItemStatus(@PathVariable("orderItemId") Long orderItemId,@PathVariable("orderStatus") OrderStatus orderStatus) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrderItemStatus(orderItemId, orderStatus));
    }

    @DeleteMapping("/itemId/{orderItemId}/orderId/{orderId}")
    public ResponseEntity<MessageResponse> cancelOrderItem(@PathVariable("orderItemId") Long orderItemId,@PathVariable("orderId") Long orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.cancelOrderItem(orderItemId, orderId));
    }

    @DeleteMapping("/orderId/{orderId}/userId/{userId}")
    public ResponseEntity<MessageResponse> deleteOrder(@PathVariable("orderId") Long orderId,@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.deleteOrder(orderId, userId));
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<OrderInfo>> getOrderByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderByUserId(userId));
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderInfo>> getAllOrder() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrder());
    }
}
