package com.project.warmup.Controllers;

import com.project.warmup.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign_up")
    public ResponseEntity save(@RequestParam String username, @RequestParam String password){

        userService.save(username,password);

        return ResponseEntity.ok("Hello " + username);
    }


}
