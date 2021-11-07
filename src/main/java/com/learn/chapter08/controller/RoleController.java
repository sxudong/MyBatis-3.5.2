package com.learn.chapter08.controller;

import com.learn.chapter08.dao.pojo.Role;
import com.learn.chapter08.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 标题：角色控制器<br>
 * 描述：角色控制器<br>
 * 时间：2018/05/24<br>
 *
 * @author zc
 **/
//@RestController
@Controller

public class RoleController {

    @Resource
    private  RoleService roleService;

    @GetMapping("/role")
    @ResponseBody
    public Role getRole(@RequestParam("id") String id) {
        Role role = roleService.findOne(Integer.parseInt(id));
        return role;
    }

    @PostMapping("/role/save")
    @ResponseBody
    public String pstUser(@RequestBody Role role) {
        System.out.println(role);
        roleService.insert(role);
        return "OK";
    }

    // @RequestParam("type")String type
//    @GetMapping("/role/{id}")
//    @ResponseBody
//    public String getRole2(@PathVariable("id") int id){
//        Role role = roleService.findOne(id);
//        System.out.println("Role: " + role);
//        return "home";
//    }

}
