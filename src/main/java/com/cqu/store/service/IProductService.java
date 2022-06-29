package com.cqu.store.service;
import com.cqu.store.entity.Product;

import java.util.List;

/** ������Ʒ���ݵ�ҵ���ӿ� */
public interface IProductService {
    /**
     * ��ѯ������Ʒ��ǰ����
     * @return ������Ʒǰ�����ļ���
     */
    List<Product> findHotList();

    /**
     * ������Ʒid��ѯ��Ʒ����
     * @param id ��Ʒid
     * @return ƥ�����Ʒ���飬���û��ƥ��������򷵻�null
     */
    Product findById(Integer id);

    List<Product> findPCList();

    List<Product> SearchPC(String key);
}