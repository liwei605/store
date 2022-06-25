package com.cqu.store.service.impl;
import com.cqu.store.entity.Address;
import com.cqu.store.entity.Order;
import com.cqu.store.entity.OrderItem;
import com.cqu.store.entity.User;
import com.cqu.store.mapper.OrderMapper;
import com.cqu.store.mapper.UserMapper;
import com.cqu.store.service.IAddressService;
import com.cqu.store.service.ICartService;
import com.cqu.store.service.IOrderService;
import com.cqu.store.service.IUserService;
import com.cqu.store.service.ex.InsertException;
import com.cqu.store.service.ex.OrderNotFound;
import com.cqu.store.service.ex.UserNotFoundException;
import com.cqu.store.vo.CartVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;


@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService cartService;

    @Transactional
    @Override
    public Order create(Integer aid, Integer[] cids, Integer uid, String username) {
        // 创建当前时间对象
        Date now = new Date();
        // 根据cids查询所勾选的购物车列表中的数据
        List<CartVO> carts = cartService.getVOByCids(uid, cids);
        // 计算这些商品的总价
        long totalPrice = 0;
        for (CartVO cart : carts) {
            totalPrice += cart.getRealPrice() * cart.getNum();
        }
        // 创建订单数据对象
        Order order = new Order();
        // 补全数据：uid
        order.setUid(uid);
        // 查询收货地址数据
        Address address = addressService.getByAid(aid, uid);
        // 补全数据：收货地址相关的6项
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        // 补全数据：totalPrice
        order.setTotalPrice(totalPrice);
        // 补全数据：status
        order.setStatus(0);
        // 补全数据：下单时间
        order.setOrderTime(now);
        // 补全数据：日志
        order.setCreatedUser(username);
        order.setCreatedTime(now);
        order.setModifiedUser(username);
        order.setModifiedTime(now);
        // 插入订单数据
        Integer rows1 = orderMapper.insertOrder(order);
        if (rows1 != 1) {
            throw new InsertException("插入订单数据时出现未知错误，请联系系统管理员");
        }
        // 遍历carts，循环插入订单商品数据
        for (CartVO cart : carts) {
        // 创建订单商品数据
            OrderItem item = new OrderItem();
        // 补全数据：setOid(order.getOid())
            item.setOid(order.getOid());
// 补全数据：pid, title, image, price, num
            item.setPid(cart.getPid());
            item.setTitle(cart.getTitle());
            item.setImage(cart.getImage());
            item.setPrice(cart.getRealPrice());
            item.setNum(cart.getNum());
// 补全数据：4项日志
            item.setCreatedUser(username);
            item.setCreatedTime(now);
            item.setModifiedUser(username);
            item.setModifiedTime(now);
// 插入订单商品数据
            Integer rows2 = orderMapper.insertOrderItem(item);
            if (rows2 != 1) {
                throw new InsertException("插入订单商品数据时出现未知错误，请联系系统管理员");
            }
        }
// 返回
        return order;
    }

    @Override
    public List<Order> getOrder(Integer uid) {
        List<Order> orderlist=  orderMapper.findOrderByuid(uid);
        //补全支付时间信息
        for (int i =0; i<orderlist.size();i++)
        {
            orderlist.get(i).setPayTime(new Date());
        }
        return  orderlist;
    }

    @Override
    public List<OrderItem> getOrderItemByoid(Integer oid) {
        //通过单号获取物品
        List<OrderItem> orderItemlist = orderMapper.findOrderItemByoid(oid);

        return  orderItemlist;
    }


    @Override
    public Integer deleteOrderByoid(Integer oid, Integer uid,String username) {
         User user= userMapper.findByUsername(username);
        if(user==null)
        {
            throw new UserNotFoundException("user not found!");
        }
        //删除用户的某个订单
        int row =orderMapper.deleteOrderByoid(oid,uid);
        if(row==0)
        {
            throw new OrderNotFound("order not found!");
        }
        int  Itemrow = orderMapper.deleteOrderItemByoid(oid);
        if(Itemrow==0)
        {
            throw new OrderNotFound("This order Exception,no orderItem in this order!");
        }
        //返回删除的物品的个数
        return Itemrow;
    }


//    public  List<OrderItem> showOrderItem(Integer uid, String username) {
//        Order order= orderMapper.findOrderByuid(uid);
//        //补全数据：支付订单的时间
//        order.setPayTime(new Date());
//        //获取订单号码
//        Integer oid= order.getOid();
//
//        //通过oid 查找订单物品
//        List<OrderItem> list= orderMapper.findOrderItemByoid(oid);
//
//        //返回订单下物品列表
//        return  list;
//    }
//
//    @Override
//    public Order showOrder(Integer uid, String username) {
//        Order order= orderMapper.findOrderByuid(uid);
//        //获取订单号码
//        order.setPayTime(new Date());
//
//        return  order;
//    }
}
