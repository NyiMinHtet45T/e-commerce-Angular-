package org.example.onlineshopbackend.model.service;

import lombok.RequiredArgsConstructor;
import org.example.onlineshopbackend.api.input.OrderRequest;
import org.example.onlineshopbackend.api.output.MessageResponse;
import org.example.onlineshopbackend.api.output.OrderInfo;
import org.example.onlineshopbackend.exception.ApiBusinessException;
import org.example.onlineshopbackend.model.entity.*;
import org.example.onlineshopbackend.model.repo.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.example.onlineshopbackend.model.entity.OrderItem.OrderStatus;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserRepo userRepo;
    private final AddressRepo addressRepo;
    private final CartRepo cartRepo;
    private final CartService cartService;
    private final OrderRepo orderRepo;
    private final OrderItemRepo orderItemRepo;

    @Transactional
    public MessageResponse createOrder(OrderRequest orderRequest) {
        Order order = initOrder(orderRequest);
        Cart cart = cartRepo.findCartByUserId(orderRequest.userId()).orElseThrow(() -> new ApiBusinessException("Cart not found"));
        List<OrderItem> orderItemList = new ArrayList<>();

        for (CartItem cartItem : cart.getCartItemList()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());
            orderItem.setOrderStatus(OrderStatus.Pending);
            orderItem.setTrackingId(new UUID(0, 0));
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItemList.add(orderItem);
        }

        var totalAndPrice = cartService.cartTotalAndPrice(orderRequest.userId());

        order.setCreatedAt(LocalDate.now());
        order.setTotalItem(totalAndPrice.getTotalQuantity());
        order.setTotalPrice(totalAndPrice.getTotalPrice());
        order.setOrderItemList(orderItemList);

        orderRepo.save(order);
        cartService.clearCart(orderRequest.userId());
        return new MessageResponse("Successfully Order!");
    }

    public MessageResponse updateOrderItemStatus(Long orderItemId, OrderStatus orderStatus) {
        OrderItem orderItem = orderItemRepo.findById(orderItemId).orElseThrow(() -> new ApiBusinessException("Order item not found"));
        orderItem.setOrderStatus(orderStatus);
        orderItemRepo.save(orderItem);
        return new MessageResponse("Order item updated!");
    }

    public MessageResponse cancelOrderItem(Long orderItemId, Long orderId) {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        OrderItem orderItem = orderItemRepo.findById(orderItemId).orElse(null);
        if (orderItem == null) {
            throw new ApiBusinessException("Order item not found");
        }
        order.getOrderItemList().remove(orderItem);
        orderRepo.save(order);
        return new MessageResponse("Order item cancelled!");
    }

    public MessageResponse deleteOrder(Long orderId, Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User Not Found!"));
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new ApiBusinessException("Order Not Found"));
        user.getOrders().remove(order);
        orderRepo.delete(order);
        userRepo.save(user);
        return new MessageResponse("Order deleted!");
    }

    public List<OrderInfo> getOrderByUserId(Long userId) {
        return orderRepo.findOrderByUserId(userId)
                .orElseGet(ArrayList::new)
                .stream()
                .map(OrderInfo::getOrderInfo)
                .toList();
    }

    public List<OrderInfo> getAllOrder() {
        return orderRepo.findAll()
                .stream()
                .map(OrderInfo::getOrderInfo)
                .toList();
    }

    private Order initOrder(OrderRequest orderRequest) {
        Order order = new Order();
        User user = userRepo.findById(orderRequest.userId()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        order.setUser(user);
        user.addOrder(order);
        Address address = addressRepo.findById(orderRequest.addressId()).orElseThrow(() -> new ApiBusinessException("Address not found"));
        order.setAddress(address);
        return order;
    }




}
