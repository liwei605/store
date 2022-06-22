package com.cqu.store.service;


import com.cqu.store.vo.FavoriteVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FavoriteServiceTests {
    @Autowired IFavoriteService favoriteService;

    @Test
    public  void insert()
    {
        favoriteService.insertFavorite(3,1000003,"user03");
    }

    @Test
    public  void delete()
    {
        favoriteService.deleteFavorite(7,3);
    }
    @Test
    public void show()
    {
        List<FavoriteVO> list=favoriteService.findByUid(3);
        for(FavoriteVO f:list)
        {
            System.out.println(f);
        }
    }
}
