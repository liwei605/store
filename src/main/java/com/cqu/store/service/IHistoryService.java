package com.cqu.store.service;

import com.cqu.store.entity.History;
import com.cqu.store.vo.HistoryVO;

import java.util.List;

public interface IHistoryService {

    void insertHistory(Integer uid,Integer pid,String username);

    List<HistoryVO> findByUid(Integer uid);

    void deleteHistory(Integer hid,Integer uid);
}
