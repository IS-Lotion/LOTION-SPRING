package lgl.lotion.controller;

import lgl.lotion.repository.UserRepository;
import lgl.lotion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("checkDuplicateEmail")
    public ResponseEntity<Boolean> checkDuplicateEmail(String email) {
        Boolean isDuplicate = userService.checkDuplicateEmail(email);

        return new ResponseEntity<Boolean>(isDuplicate, HttpStatus.OK);
    }

//    @PostMapping("signUp")
//    public ResponseEntity<Boolean> signUp(String email) {
//    }
}
