package lv.venta.fitness.controllers;

import lv.venta.fitness.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController
{

    @Autowired
    private IUserService userService;

    @GetMapping("/error")
    public String getError(Model model){
        model.addAttribute("packetError", "Error");
        return "error-page";
    }

    @GetMapping("/showAll")
    public String getAllUsers(Model model){
        model.addAttribute("user", userService.selectAllUsers());
        return "all-user-page";
    }



}
