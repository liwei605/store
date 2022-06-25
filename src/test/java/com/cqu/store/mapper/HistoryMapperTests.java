package com.cqu.store.mapper;

import com.cqu.store.entity.History;
import com.cqu.store.vo.HistoryVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HistoryMapperTests {
    @Autowired
    private  HistoryMapper historyMapper;

    @Test
    public void insert(){
        History f=new History();
        f.setPid(10000003);
        f.setUid(5);
        int rows=historyMapper.insertHistory(f);
        System.out.println(rows);
    }

    @Test
    public void findByUid()
    {
        Integer uid=3;
        List<HistoryVO> list=historyMapper.findByUid(uid);
        for (HistoryVO f:list)
        {
            System.out.println(f);
        }
    }

    @Test
    public void delete()
    {
        Integer fid=1;
        Integer rows=historyMapper.deleteHistory(3);
        System.out.println(rows);
    }

    @Test
    public  void findByUidAndPid()
    {
        List<History>list=historyMapper.findByUidAndPid(3,10000003);
        for (History f:list)
        {
            System.out.println(f);
        }

    }
}
