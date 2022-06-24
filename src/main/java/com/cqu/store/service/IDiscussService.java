package com.cqu.store.service;

import com.cqu.store.entity.Discuss;

import java.util.List;

public interface IDiscussService {

    /**
     *  通过pid查询该商品的评论
     * @param pid
     * @return
     */
    List<Discuss> searchByPid(Integer pid);


    Integer insertDiscuss(Integer uid, Integer pid, String discussion);
}
