package com.database.medicine.service;

import com.database.medicine.Exceptions.UnknownUserException;
import com.database.medicine.entity.User;
import com.database.medicine.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Getter
@RequiredArgsConstructor
public class UserService {

    private final Log logger = LogFactory.getLog(this.getClass());

    private final UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public Optional<User> findByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    public boolean makeAdmin(Integer id) {
        return makeAdmin(userRepository.findById(id).orElseThrow(UnknownUserException::new));
    }

    public boolean makeAdmin(User user) {
        if (user.getRole().toString().equalsIgnoreCase("ADMIN")) {
            logger.warn("Unknown error: " + user.getRole().toString() + " not equals to 'user'");
            return false;
        }
        else {
            userRepository.changeToAdmin(user.getId());
            return true;
        }
    }

    public boolean makeUser(Integer id) {
        return makeUser(userRepository.findById(id).orElseThrow(UnknownUserException::new));
    }

    public boolean makeUser(User user) {
        if (user.getRole().toString().equalsIgnoreCase("USER")) {
            logger.warn("Unknown error: " + user.getRole().toString() + " not equals to 'user'");
            return false;
        }
        else {
            userRepository.changeToUser(user.getId());
            return true;
        }
    }
}
