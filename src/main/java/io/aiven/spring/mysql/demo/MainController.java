package io.aiven.spring.mysql.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/demo")
public class MainController {
    @Autowired
    private UserRepo userRepo;

    @PostMapping(path = "/add")
    public @ResponseBody User addNewUser(@RequestParam String name, @RequestParam String email) {
        User springUser = new User();
        springUser.setName(name);
        springUser.setEmail(email);
        userRepo.save(springUser);
        return springUser;
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUser() {
        return userRepo.findAll();
    }

    @PostMapping(path = "/update")
    public @ResponseBody User updateUser(@RequestParam Integer id, @RequestParam String name, @RequestParam String email) {
        Optional<User> optionalUser = userRepo.findById(id);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setName(name);
            existingUser.setEmail(email);
            userRepo.save(existingUser);
            return existingUser;
        } else {
            return null;
        }
    }

    @GetMapping(path = "/deleteOne")
    public @ResponseBody String deleteOne(@RequestParam Integer id) {
        User springUser = new User();
        Integer i = springUser.setId(id);
        userRepo.deleteById(i);
        return "Deleted";
    }

    @GetMapping(path = "/delete")
    public @ResponseBody String deleteAllUser() {
        userRepo.deleteAll();
        return " All User Deleted";
    }
}
