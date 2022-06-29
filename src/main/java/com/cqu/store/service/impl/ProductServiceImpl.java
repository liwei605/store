package com.cqu.store.service.impl;

import com.cqu.store.entity.Product;
import com.cqu.store.mapper.ProductMapper;
import com.cqu.store.service.IProductService;
import com.cqu.store.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/** ������Ʒ���ݵ�ҵ���ʵ���� */
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findHotList() {
        List<Product> list = productMapper.findHotList();
        for (Product product : list) {
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public List<Product> findPCList() {
        List<Product> list = productMapper.findPCList();
        for (Product product : list) {
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public List<Product> SearchPC(String key) {
        List<Product> list = productMapper.SearchPC(key);
        return list;
    }

    @Override
    public Product findById(Integer id) {
        // ���ݲ���id����˽�з���ִ�в�ѯ����ȡ��Ʒ����
        Product product = productMapper.findById(id);
        // �жϲ�ѯ����Ƿ�Ϊnull
        if (product == null) {
            // �ǣ��׳�ProductNotFoundException
            throw new ProductNotFoundException("���Է��ʵ���Ʒ���ݲ�����");
        }
        // ����ѯ����еĲ�����������Ϊnull
        product.setPriority(null);
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);
        // ���ز�ѯ���
        return product;
    }
}