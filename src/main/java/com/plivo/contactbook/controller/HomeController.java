package com.plivo.contactbook.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("contact-book")
public class HomeController {

    @RequestMapping("ping")
    public String ping()
    {
        Date now=new Date();
        return "Hello World:"+now;
    }
}
