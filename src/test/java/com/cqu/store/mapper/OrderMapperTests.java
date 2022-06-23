package com.cqu.store.mapper;
import com.cqu.store.mapper.OrderMapper;
import com.cqu.store.entity.Order;
import com.cqu.store.entity.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTests {
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void insertOrder() {
        Order order = new Order();
        order.setUid(31);
        order.setRecvName("收货人1");
        Integer rows = orderMapper.insertOrder(order);
        System.out.println("rows=" + rows);
    }
    @Test
    public void insertOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(2);
        orderItem.setTitle("高档本子");
        Integer rows = orderMapper.insertOrderItem(orderItem);
        System.out.println("rows=" + rows);
    }
    @Test
    public void findOrderByuid() {
        List<Order> orderlist= orderMapper.findOrderByuid(10);
        System.err.println(orderlist);
    }


    @Test
    public void findOrderItemByoid() {
        List<Order> orderlist= orderMapper.findOrderByuid(10);
        for (int i=0;i<orderlist.size();i++)
        {
            List<OrderItem>list= orderMapper.findOrderItemByoid(orderlist.get(i).getOid());
            System.err.println(list);
            System.err.println();
            System.err.println();
        }


    }
}