package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jie Zhao
 * @date 2024/9/28 23:04
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/greeting")
    public String hello(String name) {
        return "hello" + name;
    }
}
