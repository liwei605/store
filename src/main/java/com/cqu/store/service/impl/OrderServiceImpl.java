package com.cqu.store.service.impl;
import com.cqu.store.entity.Address;
import com.cqu.store.entity.Order;
import com.cqu.store.entity.OrderItem;
import com.cqu.store.mapper.OrderMapper;
import com.cqu.store.service.IAddressService;
import com.cqu.store.service.ICartService;
import com.cqu.store.service.IOrderService;
import com.cqu.store.service.ex.InsertException;
import com.cqu.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;


public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService cartService;
    @Transactional
    @Override
    public Order create(Integer aid, Integer[] cids, Integer uid, String username) {
        Order order = new Order();
        return order;
        // 创建当前时间对象
//        Date now = new Date();
//        // 根据cids查询所勾选的购物车列表中的数据
//        List<CartVO> carts = cartService.getVOByCids(uid, cids);
//        // 计算这些商品的总价
//        long totalPrice = 0;
//        for (CartVO cart : carts) {
//            totalPrice += cart.getRealPrice() * cart.getNum();
//        }
//        // 创建订单数据对象
//        Order order = new Order();
//        // 补全数据：uid
//        order.setUid(uid);
//        // 查询收货地址数据
//        Address address = addressService.getByAid(aid, uid);
// 补全数据：收货地址相关的6项
// 补全数据：totalPrice
// 补全数据：status
// 补全数据：下单时间
// 补全数据：日志
// 插入订单数据
// 遍历carts，循环插入订单商品数据
// 创建订单商品数据
// 补全数据：oid(order.getOid())
// 补全数据：pid, title, image, price, num
// 补全数据：4项日志
// 插入订单商品数据
// 返回
    }
}
