package com.cqu.store.controller;

import com.cqu.store.entity.History;
import com.cqu.store.service.IHistoryService;
import com.cqu.store.util.JsonResult;
import com.cqu.store.vo.HistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("history")
@RestController
public class HistoryController  extends BaseController{
    @Autowired
    IHistoryService historyService;

    @RequestMapping("add_to_history")
    public JsonResult<Void> addToFavorite(Integer pid, HttpSession session)
    {
        historyService.insertHistory(getuidFromSession(session),pid,getUsernameFromSession(session));
        return  new JsonResult<Void>(OK);
    }

    @GetMapping({"","/"})
    public JsonResult<List<HistoryVO>> findByUid(HttpSession session)
    {
        Integer uid=getuidFromSession(session);
        List<HistoryVO> data=historyService.findByUid(uid);
        return new JsonResult<>(OK,data);
    }
    @RequestMapping("{hid}/cancelHistory")
    public JsonResult<Void> deleteFavorite(@PathVariable("hid") Integer hid, HttpSession session )
    {
        Integer uid=getuidFromSession(session);
        historyService.deleteHistory(hid,uid);
        return  new JsonResult<Void>(OK);
    }
}
