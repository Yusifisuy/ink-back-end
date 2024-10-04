package az.inkstudio.backend.controller;

import az.inkstudio.backend.entity.User;
import az.inkstudio.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
       return userService.saveOneUser(user);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        //Custom Exception
        return userService.getByUserId(userId);
    }


    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        //delete or avtive-deactive?
         userService.deleteUser(userId);
    }
}
