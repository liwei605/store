package com.cqu.store.mapper;

import com.cqu.store.entity.District;

import java.util.List;


/**
 *  省市区列表查询接口
 * @Author supreme
 * @Date 2022/6/20 15:50
 */

public interface DistrictMapper {
    /**
     *  根据父code 查询区域信息
     * @param parent            父代号
     * @return List<District>   某父区域下的全部区域列表
     */

    List<District> findByParent(String parent);
}
