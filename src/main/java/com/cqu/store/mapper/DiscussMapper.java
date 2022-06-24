package com.cqu.store.mapper;

import com.cqu.store.entity.Discuss;

import java.util.List;

public interface DiscussMapper {

    /**
     *  通过pid查询该商品的评论
     * @param pid
     * @return
     */
    List<Discuss> searchByPid(Integer pid);

    /**
     *  新增评论
     * @param discuss
     * @return
     */
    Integer insertDiscuss(Discuss discuss);
}
