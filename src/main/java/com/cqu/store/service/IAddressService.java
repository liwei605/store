package com.cqu.store.service;

import com.cqu.store.entity.Address;

import java.util.List;

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

    /**
     * 根据用户uid获取收货地址信息
     * @param uid               用户uid
     * @return List<Address>    收货地址信息列表
     */
    List<Address> getByUid(Integer uid);

    /**
     * 修改某个用户某条收货地址为默认地址
     * @param aid       收货地址id
     * @param uid       用户uid
     * @param username  修改者
     */
    void setDefault(Integer aid,Integer uid,String username);

    /**
     *  删除用户选中的收货地址
     * @param aid       收货地址aid
     * @param uid       用户uid
     * @param username  用户名
     */
    void deleteAddress(Integer aid, Integer uid, String username);

    /**
     * 根据收货地址数据的id，查询收货地址详情
     * @param aid 收货地址id
     * @param uid 归属的用户id
     * @return 匹配的收货地址详情
     */
    Address getByAid(Integer aid, Integer uid);
}
