package com.cqu.store.service.impl;

import com.cqu.store.entity.Favorite;
import com.cqu.store.mapper.FavoriteMapper;
import com.cqu.store.service.IFavoriteService;
import com.cqu.store.service.ex.AccessDeniedException;
import com.cqu.store.service.ex.DeleteException;
import com.cqu.store.service.ex.FavoriteNotFoundException;
import com.cqu.store.service.ex.InsertException;
import com.cqu.store.vo.FavoriteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class FavoriteServiceImpl implements IFavoriteService {
    @Autowired
    private FavoriteMapper favoriteMapper;

    @Override
    public  void insertFavorite(Integer uid,Integer pid,String username)
    {
        Integer rows=0;
        Date date=new Date();
        //查询是否收藏已存在
        Favorite result=favoriteMapper.findByUidAndPid(uid,pid);
        //不存在则添加到收藏夹
        if(result==null)
        {
            result=new Favorite();
            result.setPid(pid);
            result.setUid(uid);
            result.setCreatedTime(date);
            result.setModifiedTime(date);
            result.setCreatedUser(username);
            result.setModifiedUser(username);
            rows=favoriteMapper.insertFavorite(result);
            if (rows!=1)
            {
                throw new InsertException("收藏失败");
            }
        }else {
            //已经存在不需要再收藏
            throw new InsertException("已收藏");
        }

    }

    @Override
    public  List<FavoriteVO> findByUid(Integer uid)
    {
        return favoriteMapper.findByUid(uid);
    }

    @Override
    public  void deleteFavorite(Integer fid,Integer uid)
    {
        Integer rows=0;
        Date date=new Date();
        //查询是否收藏已存在
        Favorite result=favoriteMapper.findByFid(fid);
        if (result==null)
        {
            throw  new FavoriteNotFoundException("并未收藏该商品，取消收藏失败");
        }
        else
        {
            if(uid.equals(result.getUid())==false)
            {
                throw  new AccessDeniedException("非法访问");
            }
            rows=favoriteMapper.deleteFavorite(fid);
            if(rows!=1)
            {
                throw  new DeleteException("取消收藏失败，请联系管理员");
            }
        }
    }




}
