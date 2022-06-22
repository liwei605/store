package com.cqu.store.service;

import com.cqu.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTests  {

    @Autowired
    private IAddressService addressService;

    @Test
    public void addNewAddress(){
        Address address = new Address();
        address.setPhone("18887234623");
        address.setName("supreme");
        addressService.addNewAddress(23,"π‹¿Ì‘±",address);
    }

    @Test
    public void setDefault(){
        addressService.setDefault(5,15,"zxc");
    }

    @Test
    public void deleteAddress(){
        addressService.deleteAddress(15,23,"123");
    }
}
