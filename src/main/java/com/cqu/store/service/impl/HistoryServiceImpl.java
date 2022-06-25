package com.cqu.store.service.impl;

import com.cqu.store.entity.History;
import com.cqu.store.mapper.HistoryMapper;
import com.cqu.store.service.IHistoryService;
import com.cqu.store.service.ex.AccessDeniedException;
import com.cqu.store.service.ex.DeleteException;
import com.cqu.store.service.ex.HistoryNotFound;
import com.cqu.store.service.ex.InsertException;
import com.cqu.store.vo.HistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HistoryServiceImpl implements IHistoryService {

    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public void insertHistory(Integer uid,Integer pid,String username)
    {
        Integer rows=0;
        Date date=new Date();
        History result=new History();
        result.setPid(pid);
        result.setUid(uid);
        result.setCreatedTime(date);
        result.setModifiedTime(date);
        result.setCreatedUser(username);
        result.setModifiedUser(username);
        rows=historyMapper.insertHistory(result);
        if (rows!=1)
        {
            throw new InsertException("浏览足迹记录失败");
        }
    }

    @Override
    public List<HistoryVO> findByUid(Integer uid) {
        return historyMapper.findByUid(uid);
    }

    @Override
    public void deleteHistory(Integer hid, Integer uid) {
        Integer rows=0;
        Date date=new Date();
        //查询是否收藏已存在
        History result=historyMapper.findByHid(hid);
        if (result==null)
        {
            throw  new HistoryNotFound("未找到该浏览记录");
        }
        else
        {
            if(uid.equals(result.getUid())==false)
            {
                throw  new AccessDeniedException("非法访问");
            }
            rows=historyMapper.deleteHistory(hid);
            if(rows!=1)
            {
                throw  new DeleteException("删除浏览记录失败，请联系管理员");
            }
        }
    }

    @Override
    public void deleteAllHistory(Integer uid) {
        List<HistoryVO> list=historyMapper.findByUid(uid);
        Integer hid=0;
        Integer rows=0;
        for(int i=0;i<list.size();i++)
        {
            hid=list.get(i).getHid();
            rows=historyMapper.deleteHistory(hid);
            if(rows!=1)
            {
                throw  new DeleteException("删除浏览记录失败，请联系管理员");
            }
        }
    }
}
