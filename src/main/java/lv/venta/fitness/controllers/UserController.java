package lv.venta.fitness.controllers;

import lv.venta.fitness.models.User;
import lv.venta.fitness.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/user")
public class UserController
{

    @Autowired
    private IUserService userService;

    @GetMapping("/showAll")
    Collection<User> getAllUsers(Model model){
        return userService.selectAllUsers();
    }

}
