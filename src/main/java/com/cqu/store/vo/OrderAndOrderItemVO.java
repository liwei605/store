package com.cqu.store.vo;

import com.cqu.store.entity.BaseEntity;
import com.cqu.store.entity.Order;
import com.cqu.store.entity.OrderItem;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class OrderAndOrderItemVO extends BaseEntity implements Serializable {

    private Order order;
    private List<OrderItem> orderItemList;

    @Override
    public String toString() {
        return "OrderAndOrderItemVO{" +
                "order=" + order +
                ", orderItemList=" + orderItemList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderAndOrderItemVO)) return false;
        OrderAndOrderItemVO that = (OrderAndOrderItemVO) o;
        return Objects.equals(getOrder(), that.getOrder()) && Objects.equals(getOrderItemList(), that.getOrderItemList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrder(), getOrderItemList());
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
