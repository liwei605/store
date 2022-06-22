package com.cqu.store.service;

import com.cqu.store.entity.Favorite;
import com.cqu.store.vo.FavoriteVO;

import java.util.List;

public interface IFavoriteService {

    /**
     * 添加商品到收藏夹
     * @param uid
     * @param pid
     * @param username
     * @return
     */
    void insertFavorite(Integer uid,Integer pid,String username);

    /**
     * 查询某用户收藏夹信息
     * @param uid
     * @return
     */
    List<FavoriteVO> findByUid(Integer uid);

    /**
     * 取消收藏
     * @param fid
     * @return
     */
    void deleteFavorite(Integer fid,Integer uid);


}
