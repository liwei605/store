package com.cqu.store.service.impl;

import com.cqu.store.entity.Cart;
import com.cqu.store.entity.Product;
import com.cqu.store.mapper.CartMapper;
import com.cqu.store.mapper.ProductMapper;
import com.cqu.store.service.ICartService;
import com.cqu.store.service.ex.CartNotFoundException;
import com.cqu.store.service.ex.InsertException;
import com.cqu.store.service.ex.UpdateException;
import com.cqu.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    //依赖于购物车的持久层和商品的持久层
    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;


    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
        //查询当前商品是否已经在购物车中
        Cart result = cartMapper.findByUidAndPid(uid,pid);
        Date date = new Date();
        if(result == null){
            Cart cart = new Cart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            //价格，来自于商品中的数据
            Product product =productMapper.findById(pid);
            cart.setPrice(product.getPrice());

            cart.setCreatedUser(username);
            cart.setModifiedUser(username);
            cart.setCreatedTime(date);
            cart.setModifiedTime(date);

            Integer rows = cartMapper.insert(cart);
            if(rows!=1){
                throw new InsertException("添加购物车失败！");
            }
        }else{
            Integer num = result.getNum()+amount;
            Integer rows = cartMapper.updateNumByCid(result.getCid(),num,username,date);
            if(rows!=1){
                throw new UpdateException("添加购物车失败！");
            }
        }
    }

    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        return cartMapper.findVOByUid(uid);
    }

    public Integer addNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出CartNotFoundException
            throw new CartNotFoundException("尝试访问的购物车数据不存在");
        }

        Integer num = result.getNum() + 1;

        // 创建当前时间对象，作为modifiedTime
        Date now = new Date();
        Integer rows = cartMapper.updateNumByCid(cid, num, username, now);
        if (rows != 1) {
            throw new UpdateException("增加失败，请联系系统管理员");
        }

        // 返回新的数量
        return num;
    }

    @Override
    public Integer reduceNum(Integer cid, Integer uid, String username) {
        // 调用findByCid(cid)根据参数cid查询购物车数据
        Cart result = cartMapper.findByCid(cid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出CartNotFoundException
            throw new CartNotFoundException("尝试访问的购物车数据不存在");
        }
        if(result.getNum()==1){
            return 1;
        }
        Integer num = result.getNum() - 1;
        // 创建当前时间对象，作为modifiedTime
        Date now = new Date();
        Integer rows = cartMapper.updateNumByCid(cid, num, username, now);
        if (rows != 1) {
            throw new UpdateException("减少商品数量失败，请联系系统管理员");
        }
//        if(num==0)cartMapper.deleteByCid(cid);
        // 返回新的数量
        return num;
    }
    @Override
    public void deleteCart(Integer cid, Integer uid, String username){
        // 调用findByCid(cid)根据参数cid查询购物车数据
        Cart result = cartMapper.findByCid(cid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出CartNotFoundException
            throw new CartNotFoundException("尝试访问的购物车数据不存在");
        }

        boolean ret = cartMapper.deleteByCid(cid);

    }
    @Override
    public List<CartVO> getVOByCids(Integer uid, Integer[] cids){
        List<CartVO> list = cartMapper.findVOByCids(cids);
        Iterator<CartVO> it = list.iterator();
        while (it.hasNext()) {
            CartVO cart = it.next();
            if (!cart.getUid().equals(uid)) {
                it.remove();
            }
        }
        return list;
    }
}