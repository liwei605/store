package com.cqu.store.mapper;

import com.cqu.store.entity.Discuss;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiscussMapperTests {
    @Autowired
    private DiscussMapper discussMapper;

    @Test
    public  void insertDiscuss(){
        Discuss discuss = new Discuss();
        discuss.setUid(8);
        discuss.setPid(10000017);
        discuss.setDiscussion("unbelievable!");
        Date date = new Date();
        int ret = discussMapper.insertDiscuss(discuss);
        System.out.println(ret);


    }

    @Test
    public void find(){
        List<Discuss> list = discussMapper.searchByPid(442);
        System.out.println(list);

    }


}
