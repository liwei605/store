package com.cqu.store.mapper;
import com.cqu.store.entity.Order;
import com.cqu.store.entity.OrderItem;

import java.util.List;

public interface OrderMapper {
    /**
     * 插入订单数据
     * @param order 订单数据
     * @return 受影响的行数
     */
    Integer insertOrder(Order order);
    /**
     * 插入订单商品数据
     * @param orderItem 订单商品数据
     * @return 受影响的行数
     */
    Integer insertOrderItem(OrderItem orderItem);

    //通过用户id来找订单ID
    List<Order> findOrderByuid(Integer uid);

    //通过订单id来找该订单下的物品ID
    List<OrderItem> findOrderItemByoid(Integer oid);

    //通过订单id和用户id删除订单
    Integer deleteOrderByoid(Integer oid ,Integer uid);

    //通过订单id删除订单下的物品
    Integer deleteOrderItemByoid(Integer oid );
}
