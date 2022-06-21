package com.cqu.store.service;

import com.cqu.store.vo.CartVO;

import java.util.List;

public interface ICartService {
    /**
     *  把商品添加到购物车中
     * @param uid 用户id
     * @param pid 商品Id
     * @param amount 商品数量
     * @param usrname 用户名
     */
    void addToCart(Integer uid, Integer pid, Integer amount, String usrname);

    /**
     * 查询某用户的购物车数据
     * @param uid 用户id
     * @return 该用户的购物车数据的列表
     */
    List<CartVO> getVOByUid(Integer uid);

    /**
     * 将购物车中某商品的数量加1
     * @param cid 购物车数量的id
     * @param uid 当前登录的用户的id
     * @param username 当前登录的用户名
     * @return 增加成功后新的数量
     */
    Integer addNum(Integer cid, Integer uid, String username);

    /**
     * 将购物车中某商品的数量减1
     * @param cid 购物车数量的id
     * @param uid 当前登录的用户的id
     * @param username 当前登录的用户名
     * @return 增加成功后新的数量
     */
    Integer reduceNum(Integer cid, Integer uid, String username);

    void deleteCart(Integer cid, Integer uid, String username);
}