package com.cqu.store.controller;

import com.cqu.store.service.IDiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("discuss")
@RestController
public class DiscussController {
    @Autowired
    private IDiscussService discussService;


}
