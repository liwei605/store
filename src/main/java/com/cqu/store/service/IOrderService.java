package com.cqu.store.service;
import com.cqu.store.entity.Order;
import com.cqu.store.entity.OrderItem;

import java.util.List;


public interface IOrderService {
    /**
     * 创建订单
     * @param aid 收货地址的id
     * @param cids 即将购买的商品数据在购物车表中的id
     * @param uid 当前登录的用户的id
     * @param username 当前登录的用户名
     * @return 成功创建的订单数据
     */
    Order create(Integer aid, Integer[] cids, Integer uid, String username);

//    List<OrderItem> showOrderItem(Integer uid, String username) ;
//
//    Order showOrder(Integer uid, String username) ;
    //通过uid获取该用户的所有订单
    List<Order> getOrder(Integer uid) ;

    List<OrderItem> getOrderItemByoid(Integer oid) ;


    // 删除订单
    Integer deleteOrderByoid(Integer oid ,Integer uid,String username);
}
