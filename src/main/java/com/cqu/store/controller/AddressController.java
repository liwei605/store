package com.cqu.store.controller;

import com.cqu.store.entity.Address;
import com.cqu.store.service.IAddressService;
import com.cqu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("addresses")
@RestController
public class AddressController extends BaseController{
    @Autowired
    private IAddressService addressService;

    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session){
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addNewAddress(uid,username,address);
        return new JsonResult<>(OK);
    }

    @RequestMapping({"","/"})
    public JsonResult<List<Address>> getByUid(HttpSession session){
        Integer uid = getuidFromSession(session);
        List<Address> data = addressService.getByUid(uid);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("{aid}/set_default")
    public  JsonResult<Void> setDefault(@PathVariable("aid") Integer aid,HttpSession session){
        addressService.setDefault(
                aid,
                getuidFromSession(session),
                getUsernameFromSession(session)
        );
        return new JsonResult<>(OK);
    }

    @RequestMapping("{aid}/delete")
    public JsonResult<Void> deleteAddress(@PathVariable("aid") Integer aid,HttpSession session){
        addressService.deleteAddress(
                aid,
                getuidFromSession(session),
                getUsernameFromSession(session)
        );
        return new JsonResult<>(OK);
    }
}
