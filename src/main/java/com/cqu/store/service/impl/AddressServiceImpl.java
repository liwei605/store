package com.cqu.store.service.impl;

import com.cqu.store.entity.Address;
import com.cqu.store.mapper.AddressMapper;
import com.cqu.store.service.IAddressService;
import com.cqu.store.service.IDistrictService;
import com.cqu.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 收货地址实现类
 * @Author supreme
 * @Date 2022/6/20 11:16
 */
@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    //在添加用户收货地址的业务层依赖于DistrictService接口
    @Autowired
    private IDistrictService districtService;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        //1、判断是否达到地址数量上限
        Integer count = addressMapper.countByUid(uid);

        if(count>=maxCount){
            throw  new AddressCountLimitException("收获地址数量超出上限");
        }
        //2、补全省市区相关数据
        String provinceName =  districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        //3、补全数据、封装数据
        address.setUid(uid);
        Integer isDefault = count==0?1:0;
        address.setIsDefault(isDefault);
        address.setCreatedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());

        //4、插入收货地址
        Integer rows = addressMapper.insert(address);
        if(rows!=1){
            throw new InsertException("新增用户收货地址时发生未知异常");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        for(Address address:list){
//            address.setAid(null);
//            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setZip(null);
            address.setTel(null);
            address.setCreatedTime(null);
            address.setCreatedUser(null);
            address.setIsDefault(null);
            address.setModifiedTime(null);
            address.setModifiedUser(null);
        }
        return list;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address address = addressMapper.findByAid(aid);
        if(address==null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        //检测当前获取到的收货地址数据归属
        if(!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
        //先将所有收货地址设置为非默认
        Integer rows = addressMapper.updateNoneDefault(uid);
        if(rows<1){
            throw new UpdateException("更新默认地址发生异常");
        }
        //将用户选中的地址设置为默认收货地址
        rows = addressMapper.updateDefaultByAid(aid,username,new Date());
        if(rows!=1){
            throw new UpdateException("更新默认地址发生异常");
        }
    }

    @Override
    public void deleteAddress(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if(result==null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        //检测当前获取到的收货地址数据归属
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }

        Integer isDefault = result.getIsDefault();

        //根据aid删除收货地址
        Integer rows = addressMapper.deleteByAid(aid);
        if(rows!=1){
            throw new DeleteException("删除数据产生未知的异常");
        }

        Integer count = addressMapper.countByUid(uid);
        if(count==0 || isDefault==0){
            return;
        }

        Address address = addressMapper.findLastModified(uid);
        rows= addressMapper.updateDefaultByAid(address.getAid(),username,new Date());
        if(rows!=1){
            throw new UpdateException("更新数据时发生异常");
        }
    }
    @Override
    public Address getByAid(Integer aid, Integer uid) {
        // 根据收货地址数据id，查询收货地址详情
        Address address = addressMapper.findByAid(aid);
        if (address == null) {
            throw new AddressNotFoundException("尝试访问的收货地址数据不存在");
        }
        if (!address.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        address.setCreatedUser(null);
        address.setCreatedTime(null);
        address.setModifiedUser(null);
        address.setModifiedTime(null);
        return address;
    }
}
