package com.cqu.store.controller;

import com.cqu.store.entity.District;
import com.cqu.store.service.IDistrictService;
import com.cqu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 省市区地址查询控制层
 * @Author supreme
 * @Date 2022/6/20 16:46
 */

@RequestMapping("districts")
@RestController
public class DistrictController extends BaseController {
    @Autowired
    private IDistrictService  districtService;

    //凡是以districts开头的请求都会被拦截到这个方法内
    @RequestMapping({"/",""})
    public JsonResult<List<District>> getByParent(String parent){
        List<District> data = districtService.getByParent(parent);
        return new JsonResult<>(OK,data);
    }
}
