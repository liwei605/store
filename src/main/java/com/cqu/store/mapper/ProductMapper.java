package com.cqu.store.mapper;
import com.cqu.store.entity.Product;

import java.util.List;

public interface ProductMapper {
    
    /**
     * 通过商品热销优先级找商品
     * @Author supreme
     * @Date 2022/6/20 8:49
     * @return List<Product> 
     */

    List<Product> findHotList();

    /**
     * 根据商品id返回商品
     * @Author supreme
     * @Date 2022/6/20 8:50
     * @param id
     * @return Product
     */

    Product findById(Integer id);
}
