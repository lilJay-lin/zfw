package com.mimi.zfw.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.mimi.zfw.service.IUserService;

@Controller
public class CFCKController {
    @Resource
    private IUserService userService;

}
