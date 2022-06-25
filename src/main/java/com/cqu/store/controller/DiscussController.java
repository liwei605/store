package com.cqu.store.controller;

import com.cqu.store.entity.Discuss;
import com.cqu.store.service.IDiscussService;
import com.cqu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping("discuss")
@RestController
public class DiscussController extends BaseController{
    @Autowired
    private IDiscussService discussService;

    @GetMapping("{pid}/search")
    public JsonResult<List<Discuss>> searchByPid(@PathVariable("pid") Integer pid){
        List<Discuss> list = discussService.searchByPid(pid);
        for (int i=0;i<list.size();i++)
        {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(date);

            Date currentTime_2 = null;
            try {
                currentTime_2 = formatter.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.err.println(currentTime_2);
            list.get(i).setCreatedTime(currentTime_2);
        }
        return new JsonResult<List<Discuss>>(OK,list);
    }

    @RequestMapping("insert")
    public JsonResult<Void> insertDiscuss(HttpSession session, Integer pid, String discussion){
        Integer uid =getuidFromSession(session);
        discussService.insertDiscuss(uid,pid,discussion);
        return new JsonResult<>(OK);
    }
}
