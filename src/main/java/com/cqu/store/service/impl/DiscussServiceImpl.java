package com.cqu.store.service.impl;

import com.cqu.store.entity.Discuss;
import com.cqu.store.mapper.DiscussMapper;
import com.cqu.store.mapper.UserMapper;
import com.cqu.store.service.IDiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DiscussServiceImpl implements IDiscussService {

    @Autowired
    private DiscussMapper discussMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Discuss> searchByPid(Integer pid) {
        return discussMapper.searchByPid(pid);
    }

    @Override
    public Integer insertDiscuss(Integer uid, Integer pid, String discussion) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);

        Date currentTime_2 = null;
        try {
            currentTime_2 = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        System.err.println(currentTime_2);


        Discuss discuss = new Discuss();
        discuss.setUid(uid);
        discuss.setPid(pid);
        //防止评论过长超出数据库限制
        if(discussion.length()>80){
            discussion = discussion.substring(0,80);
        }
        discuss.setDiscussion(discussion);
        discuss.setCreatedTime(currentTime_2);
        discuss.setModifiedTime(currentTime_2);
        String username = userMapper.findByUid(uid).getUsername();
        discuss.setCreatedUser(username);
        discuss.setModifiedUser(username);

        int ret = discussMapper.insertDiscuss(discuss);
        return ret;


    }


}
