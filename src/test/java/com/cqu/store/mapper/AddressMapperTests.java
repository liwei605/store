package com.cqu.store.mapper;

import com.cqu.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest

public class AddressMapperTests {
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert(){
        Address address = new Address();
        address.setUid(10);
        address.setName("123123");
        addressMapper.insert(address);
    }

    @Test
    public void countByUid(){
        Integer count = addressMapper.countByUid(10);
        System.out.println(count);
    }

    @Test
    public void findByUid(){
        List<Address> list = addressMapper.findByUid(15);
        System.out.println(list);
    }

    @Test
    public void findByAid() {
        System.err.println(addressMapper.findByAid(6));
    }

    @Test
    public void updateNoneDefault(){
        addressMapper.updateNoneDefault(15);
    }

    @Test
    public void updateDefaultByAid(){
        addressMapper.updateDefaultByAid(6,"zxc",new Date());
        System.out.println(11212);
    }

    @Test
    public void deleteByAid(){
        addressMapper.deleteByAid(6);
    }

    @Test
    public void findLastModified(){
        System.out.println(addressMapper.findLastModified(15));
    }
}
