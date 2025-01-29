package org.example.onlineshopbackend.api.output;

import lombok.Getter;
import lombok.Setter;
import org.example.onlineshopbackend.model.entity.Order;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrderInfo {

    private Long id;
    private LocalDate orderDate;
    private int totalItem;
    private long totalPrice;

    private AddressInfo addressInfo;
    private List<OrderItemInfo> orderItemInfo;

    public static OrderInfo getOrderInfo(Order order) {
        List<OrderItemInfo> orderItem = order.getOrderItemList().stream()
                .map(item -> OrderItemInfo.getOrderItemInfo(item, item.getProduct()))
                .toList();
        AddressInfo address = AddressInfo.getAddressInfo(order.getAddress());

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(order.getId());
        orderInfo.setOrderDate(order.getCreatedAt());
        orderInfo.setTotalItem(order.getTotalItem());
        orderInfo.setTotalPrice(order.getTotalPrice());
        orderInfo.setAddressInfo(address);
        orderInfo.setOrderItemInfo(orderItem);
        return orderInfo;
    }

}
