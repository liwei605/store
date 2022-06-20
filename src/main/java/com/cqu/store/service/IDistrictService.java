package com.cqu.store.service;

import com.cqu.store.entity.District;

import java.util.List;

/**
 * 省市区列表信息Service层接口
 * @Author supreme
 * @Date 2022/6/20 16:04
 */

public interface IDistrictService {
    /**
     * 根据父代号查询子区域信息
     * @param parent            父代号
     * @return List<District>   子区域信息列表
     */

    List<District> getByParent(String parent);
}
