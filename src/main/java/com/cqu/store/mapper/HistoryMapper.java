package com.cqu.store.mapper;

import com.cqu.store.entity.History;
import com.cqu.store.vo.HistoryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HistoryMapper {

    /**
     * 查找用户浏览记录id
     * @param uid
     * @return
     */
    List<HistoryVO> findByUid(Integer uid);

    /**
     * 插入浏览记录
     * @param history
     * @return
     */
    Integer insertHistory(History history);


    /**
     * 删除浏览记录
     * @param hid
     * @return
     */
    Integer deleteHistory(Integer hid);

    /**
     * 查找浏览记录
     * @param uid
     * @param pid
     * @return
     */
    List<History> findByUidAndPid(@Param("uid") Integer uid, @Param("pid")Integer pid);

    /**
     * 查找浏览记录
     * @param hid
     * @return
     */
    History findByHid(@Param("hid")Integer hid);


}
