package com.uncledavecode.authorization.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public String demo() {
        return "Hello World";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hello Admin";
    }

    @GetMapping("/dba")
    public String dba() {
        return "Hello DBA";
    }

    @PostMapping("/add")
    public String add() {
        return "Hello Add User";
    }

    @GetMapping("/add")
    public String add2() {
        return "Hello get User";
    }
}
