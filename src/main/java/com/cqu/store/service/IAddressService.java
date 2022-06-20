package com.cqu.store.service;

import com.cqu.store.entity.Address;

/**
 *  收货地址业务层接口
 * @Author supreme
 * @Date 2022/6/20 11:11
 */

public interface IAddressService {

    /**
     * 新增收获地址业务
     * @param uid       用户uid
     * @param username  收货者姓名
     * @param address   插入的地址
     */
    void addNewAddress(Integer uid, String username, Address address);
}
