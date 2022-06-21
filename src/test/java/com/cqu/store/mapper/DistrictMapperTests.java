package com.cqu.store.mapper;

import com.cqu.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest

public class DistrictMapperTests {
    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void findByParent() {
        List<District> list = districtMapper.findByParent("210100");
        for(District d:list){
            System.out.println(d);
        }
    }
    @Test
    public void findNameByParent(){
        String name = districtMapper.findNameByCode("610000");
        System.out.println(name);
    }
}
