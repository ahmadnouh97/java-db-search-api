package com.example.fulltextsearchapi.User;

import com.example.fulltextsearchapi.User.models.Role;
import com.example.fulltextsearchapi.User.models.User;
import com.example.fulltextsearchapi.User.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/users/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity
                .created(URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/save").toUriString()))
                .body(userService.saveUser(user));
    }

    @PostMapping("/roles/save")
    public ResponseEntity<Role> saveUser(@RequestBody Role role) {

        return ResponseEntity
                .created(URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/roles/save").toUriString()))
                .body(userService.saveRole(role));
    }

    @PostMapping("/users/rules/add")
    public ResponseEntity<?> addRuleToUser(@RequestBody RuleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }
}

@Data
class RuleToUserForm {
    private String username;
    private String roleName;
}
