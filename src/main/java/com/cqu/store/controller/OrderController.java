package com.cqu.store.controller;
import com.cqu.store.entity.Order;
import com.cqu.store.entity.OrderItem;
import com.cqu.store.service.IOrderService;
import com.cqu.store.util.JsonResult;
import com.cqu.store.vo.OderVO;
import com.cqu.store.vo.OrderAndOrderItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

    @RequestMapping("show_order_and_orderItem")
    public JsonResult<List<OrderAndOrderItemVO>> showOrderItem(HttpSession session)
    {
        // 从Session中取出uid和username
        Integer uid = getuidFromSession(session);
        List<Order> orderList= orderService.getOrder(uid);
        List<OrderAndOrderItemVO> orderAndOrderItemVOlist =new ArrayList<>();
        for (int i=0;i<orderList.size();i++)
        {
            //新建视图对象
            OrderAndOrderItemVO orderAndOrderItemVO = new OrderAndOrderItemVO();
            //设置订单对象
            orderAndOrderItemVO.setOrder(orderList.get(i));

            //获取每个order的oid
            Integer oid= orderList.get(i).getOid();

            //通过oid寻找该订单下所有的物品
            List<OrderItem> orderItemList= orderService.getOrderItemByoid(oid);

            //设置订单物品列表对象
            orderAndOrderItemVO.setOrderItemList(orderItemList);

            //将概视图对象放入视图对象list
            orderAndOrderItemVOlist.add(orderAndOrderItemVO);
        }

        //返回视图对象list
        return new JsonResult<List<OrderAndOrderItemVO>>(OK,orderAndOrderItemVOlist);
    }


    @RequestMapping("deleteorder")
    public JsonResult<Void> deleteOrder(Integer oid,HttpSession session)
    {
        // 从Session中取出uid和username
        Integer uid = getuidFromSession(session);
        //从session中取出username
        String username =getUsernameFromSession(session);
        orderService.deleteOrderByoid(oid,uid,username);
        return new JsonResult<Void>(OK);
    }
}