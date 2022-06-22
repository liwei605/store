package com.cqu.store.mapper;

import com.cqu.store.entity.Favorite;
import com.cqu.store.vo.FavoriteVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FavoriteMapperTests {
    @Autowired
    private FavoriteMapper favoriteMapper;

    @Test
    public void insert(){
        Favorite f=new Favorite();
        f.setPid(10000003);
        f.setUid(3);
        int rows=favoriteMapper.insertFavorite(f);
        System.out.println(rows);
    }
    @Test
    public void findByUid()
    {
        Integer uid=3;
        List<FavoriteVO> list=favoriteMapper.findByUid(uid);
        for (FavoriteVO f:list)
        {
            System.out.println(f);
        }
    }

    @Test
    public void delete()
    {
        Integer fid=1;
        Integer rows=favoriteMapper.deleteFavorite(5);
        System.out.println(rows);
    }

    @Test
    public  void findByUidAndPid()
    {
        Favorite f=favoriteMapper.findByUidAndPid(3,10000001);
        System.out.println(f);
    }

}
