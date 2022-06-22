package com.cqu.store.mapper;

import com.cqu.store.entity.Favorite;
import com.cqu.store.vo.FavoriteVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//收藏夹
public interface FavoriteMapper {

    /**
     * 通过用户id 查找购物车数据
     * @param uid
     * @return
     */
    List<FavoriteVO>  findByUid(Integer uid);

    /**
     * 创建收藏数据
     * @param favorite
     * @return
     */
    Integer insertFavorite(Favorite favorite);

    /**
     * 取消收藏
     * @param fid
     * @return
     */
    Integer deleteFavorite(Integer fid);

    /**
     * 根据uid和pid查找收藏夹实体
     * @return
     */
    Favorite findByUidAndPid(@Param("uid") Integer uid,@Param("pid")Integer pid);

    /**
     * 根据收藏夹序号查找
     * @param fid
     * @return
     */
    Favorite findByFid(@Param("fid")Integer fid);
}
