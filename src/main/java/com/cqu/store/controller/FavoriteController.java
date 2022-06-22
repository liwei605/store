package com.cqu.store.controller;


import com.cqu.store.entity.Favorite;
import com.cqu.store.service.IFavoriteService;
import com.cqu.store.util.JsonResult;
import com.cqu.store.vo.FavoriteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("favorites")
@RestController
public class FavoriteController  extends BaseController{
    @Autowired
    IFavoriteService favoriteService;

    @RequestMapping("add_to_favorite")
    public JsonResult<Void> addToFavorite(Integer pid, HttpSession session)
    {
        favoriteService.insertFavorite(getuidFromSession(session),pid,getUsernameFromSession(session));
        return  new JsonResult<Void>(OK);
    }

    @GetMapping({"","/"})
    public JsonResult<List<FavoriteVO>> findByUid(HttpSession session)
    {
        Integer uid=getuidFromSession(session);
        List<FavoriteVO> data=favoriteService.findByUid(uid);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("{fid}/cancelFavorite")
    public JsonResult<Void> deleteFavorite(@PathVariable("fid") Integer fid, HttpSession session )
    {
        Integer uid=getuidFromSession(session);
        favoriteService.deleteFavorite(fid,uid);
        return  new JsonResult<Void>(OK);
    }



}
