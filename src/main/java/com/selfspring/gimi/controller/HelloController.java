package com.selfspring.gimi.controller;

import org.omg.CORBA.Object;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ckyang on 2019/6/22.
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public void hello(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.getWriter().write("hello,world");
    }

    @RequestMapping("/world")
    public String world() throws IOException {
        return "hello,world";
    }

}
