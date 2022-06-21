package com.cqu.store.mapper;

import com.cqu.store.entity.District;
import com.cqu.store.service.IDistrictService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest

public class DistrictServiceTests {
    @Autowired
    private IDistrictService districtService;

    @Test
    public void findByParent() {
        List<District> list = districtService.getByParent("86");
        for(District d:list){
            System.out.println(d);
        }
    }

}
