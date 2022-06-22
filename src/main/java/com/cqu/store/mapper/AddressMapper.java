package com.cqu.store.mapper;

import com.cqu.store.entity.Address;
import com.cqu.store.entity.District;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

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


    /**
     * 根据用户uid查询收货地址数据
     * @param uid               用户uid
     * @return List<District>   收货地址数据
     */
    List<Address> findByUid(Integer uid);

    /**
     * 根据aid查询收货地址数据
     * @param aid           收货地址aid
     * @return Address      收货地址数据，若没找到则返回null
     */
    Address findByAid(Integer aid);

    /**
     *  根据用户的uid值来修改用户收货地址设置为非默认
     * @param uid           用户uid
     * @return Integer      受影响的行数
     */
    Integer updateNoneDefault(Integer uid);


    /**
     * 根据aid设置默认地址
     * @param aid           收货地址aid
     * @param modifiedUser  修改者
     * @param modifiedTime  修改时间
     * @return Integer
     */

    Integer updateDefaultByAid(
            @Param("aid") Integer aid,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime
    );

    /**
     *  根据aid删除收货地址
     * @param aid       收货地址aid
     * @return Integer  受影响的行数
     */
    Integer deleteByAid(Integer aid);

    /**
     * 根据用户uid查询当前用户最后一次修改的收货地址
     * @param uid       用户uid
     * @return Address  最后被修改的收货地址
     */
    Address findLastModified(Integer uid);
}
