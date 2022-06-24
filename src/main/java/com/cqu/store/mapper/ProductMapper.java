package com.cqu.store.mapper;
import com.cqu.store.entity.Product;

import java.util.List;

public interface ProductMapper {
    
    /**
     * ͨ����Ʒ�������ȼ�����Ʒ
     * @Author supreme
     * @Date 2022/6/20 8:49
     * @return List<Product> 
     */

    List<Product> findHotList();

    List<Product> findPCList();

    /**
     * ������Ʒid������Ʒ
     * @Author supreme
     * @Date 2022/6/20 8:50
     * @param id
     * @return Product
     */

    Product findById(Integer id);
}
