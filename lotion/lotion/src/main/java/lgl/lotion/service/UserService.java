package lgl.lotion.service;

import lgl.lotion.dto.User;
import lgl.lotion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Boolean checkDuplicateEmail(String email) {
        List<User> userList = userRepository.findByEMAIL(email);
        return (userList.size() == 0 ? false : true);
    }
}
