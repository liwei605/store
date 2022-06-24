package com.cqu.store.service;

import com.cqu.store.entity.Discuss;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiscussServiceTests {

    @Autowired
    private IDiscussService discussService;

    @Test
    public void testSearch(){
        List<Discuss> list = discussService.searchByPid(442);
        System.out.println(list);
    }

    @Test
    public void testInsert(){
        discussService.insertDiscuss(6,10000017, "太坑了");
    }
}
