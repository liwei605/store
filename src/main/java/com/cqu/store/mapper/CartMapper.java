package com.cqu.store.mapper;

import com.cqu.store.entity.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface CartMapper {
    /**
     *  插入购物车数据
     * @param cart 购物车数据
     * @return 行数
     */
    Integer insert(Cart cart);


    /**
     *  更新数据库某个商品的数量
     * @param cid
     * @param num 更新的数量
     * @param modifiedUser
     * @param modifiedTime
     * @return 行数
     */
    Integer updateNumByCid(
            @Param("cid") Integer cid,
            @Param("num") Integer num,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据用户id和商品id查询购物车中的数据
     * @param uid 用户id
     * @param pid 商品id
     * @return 匹配的购物车数据，如果该用户的购物车中并没有该商品，则返回null
     */
    Cart findByUidAndPid(
            @Param("uid") Integer uid,
            @Param("pid") Integer pid);

}
