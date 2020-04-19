package com.plivo.contactbook.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="home")
public class HomeController {



    @RequestMapping(value = "ping")
    public String getDate()
    {
        return "Helooo spring boot";

    }
}
