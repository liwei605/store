package com.cqu.store.service.impl;

import com.cqu.store.entity.Address;
import com.cqu.store.mapper.AddressMapper;
import com.cqu.store.service.IAddressService;
import com.cqu.store.service.IDistrictService;
import com.cqu.store.service.ex.AddressCountLimitException;
import com.cqu.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

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
}
