package com.cqu.store.mapper;

import com.cqu.store.entity.Address;

/**
 *  收获地址持久层接口
 * @Author supreme
 * @Date 2022/6/20 10:21
 */

public interface AddressMapper  {
    /**
     * 插入用户得出收货地址数据
     * @param address   收获地址数据
     * @return Integer  受影响的行数
     */
    Integer insert(Address address);

    /**
     * 根据用户id统计收货地址数量
     * @param uid       用户uid
     * @return Integer  收货地址数量
     */

    Integer countByUid(Integer uid);
}
