package ru.rdavydov.tokenizier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rdavydov.tokenizier.repositories.UsersRepository;
import ru.rdavydov.tokenizier.entities.User;
import ru.rdavydov.tokenizier.utils.Hasher;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class MainController {
    @Autowired
    private UsersRepository usersRepo;

    @GetMapping()
    public String index() {

        return "users";
    }

    @PostMapping()
    public String create(@RequestParam(name = "name") String name, @RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
        //check if there is no such user:
        List<User> checkingUser = usersRepo.findByEmail(email);
        System.out.println(checkingUser.size());

        if (checkingUser.size() > 0) {
            return "not unique!";
        }

        try {
            User user = new User(name, email, password);
            usersRepo.save(user);
        } catch (Exception e) {
            return "system error with algorithm MD5";
        }

        return "saved";
    }

    @PostMapping("/login")
    public String login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
        String passwordHash = "";
        try {
            passwordHash = Hasher.md5(password);
        } catch (Exception e) {
            return "system error with algorithm MD5";
        }

        List<User> users = usersRepo.findByEmailAndPassword(email, passwordHash);

        if (users.size() > 0) {
            User user = users.get(0);
            return user.getName();
        } else {
            return "bad credentials";
        }
    }

    @GetMapping("/get")
    public String getUser(@RequestParam(name = "token") String token) {

        return "no user";
    }
}
