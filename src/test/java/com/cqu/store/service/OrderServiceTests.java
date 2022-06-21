package com.cqu.store.service;
import com.cqu.store.entity.Order;
import com.cqu.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.cqu.store.service.IOrderService;
import org.springframework.stereotype.Service;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTests {
    @Autowired
    private IOrderService orderService;
    @Test
    public void create() {
        try {
            Integer aid = 21;
            Integer[] cids = {4, 5, 6,7};
            Integer uid = 31;
            String username = "订单管理员";
            Order order = orderService.create(aid, cids, uid, username);
            System.out.println(order);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}