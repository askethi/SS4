package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImp;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

@Controller
public class UserController {

    final UserServiceImp userService;
    final RoleServiceImp roleService;

    @Autowired
    public UserController(UserServiceImp userService, RoleServiceImp roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String show(@RequestParam("username") String username, Model model) {
        model.addAttribute("user", userService.findByUsername(username));
        return "user";
    }

    @GetMapping("/admin")
    public String index(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "users/index";
    }

    @GetMapping("/admin/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.listRoles());
        return "users/new";
    }

    @PostMapping("/admin")
    public String create(@ModelAttribute User user) {
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit")
    public String edit(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.listRoles());
        return "users/edit";
    }

    @PostMapping("/admin/edit")
    public String update(User user, @RequestParam("id") int id) {
        userService.updateUserById(user, id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/show")
    public String show(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/show";
    }

    @PostMapping("/admin/delete")
    public String delete(@RequestParam("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

//    @GetMapping("/admin")
//    public String adminPage() {
//        return "users/admin";
//    }
}
