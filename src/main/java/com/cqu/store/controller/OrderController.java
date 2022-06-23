package com.cqu.store.controller;
import com.cqu.store.entity.Order;
import com.cqu.store.entity.OrderItem;
import com.cqu.store.service.IOrderService;
import com.cqu.store.util.JsonResult;
import com.cqu.store.vo.OderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController extends BaseController {
    @Autowired
    private IOrderService orderService;
    @RequestMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession
            session) {
// 从Session中取出uid和username
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
// 调用业务对象执行业务
        Order data = orderService.create(aid, cids, uid, username);
// 返回成功与数据
        return new JsonResult<Order>(OK, data);
    }

    @RequestMapping("show_order_item")
    public JsonResult<List<OrderItem>> showOrderItem(HttpSession session)
    {
        // 从Session中取出uid和username
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行业务
        List<OrderItem> data= orderService.showOrderItem(uid,username);
        // 返回成功与数据
        return new JsonResult<List<OrderItem>>(OK, data);
    }

    @RequestMapping("show_order")
    public JsonResult<OderVO> showOrder(HttpSession session){

        // 从Session中取出uid和username
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行业务
        Order order= orderService.showOrder(uid,username);
        System.err.println(order.getOrderTime());
        OderVO data =new OderVO();
        //随机订单号
        data.setO_st_id("10000000"+order.getOid());
        //设置收货人
        data.setRecv_name(order.getRecvName());
        //设置下单时间
//        data.setCreat_time(new Date());
        data.setCreat_time(order.getOrderTime().toString());
        //设置总价格
        data.setTotal_price(order.getTotalPrice().toString());

        return new JsonResult<OderVO>(OK, data);
    }
}