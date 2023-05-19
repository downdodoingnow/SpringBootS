package com.example.demo.controller;

import com.example.demo.entity.ThymeleafUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ThymeleafController {
    @RequestMapping("/index")
    public String getIndex(Model model) {
        System.out.println("index");
        List<ThymeleafUser> userList = new ArrayList<>();
        ThymeleafUser user = new ThymeleafUser("tom", "female", "17788996600");
        userList.add(user);
        model.addAttribute("userList",userList);
        return "index";
    }
}
