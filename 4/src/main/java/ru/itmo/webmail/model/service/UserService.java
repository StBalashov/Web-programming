package ru.itmo.webmail.model.service;

import com.google.common.hash.Hashing;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.*;
import ru.itmo.webmail.model.repository.UserRepository;
import ru.itmo.webmail.model.repository.impl.UserRepositoryImpl;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class UserService {
    private static final String USER_PASSWORD_SALT = "dc3475f2b301851b";

    private UserRepository userRepository = new UserRepositoryImpl();

    public void validateRegistration(User user, String password, String passwordConfirmation) throws ValidationException {
        if (user.getLogin() == null || user.getLogin().isEmpty()) {
            throw new ValidationException("Login is required");
        }
        if (!user.getLogin().matches("[a-z]+")) {
            throw new ValidationException("Login can contain only lowercase Latin letters");
        }
        if (user.getLogin().length() > 8) {
            throw new ValidationException("Login can't be longer than 8");
        }
        if (userRepository.findByLogin(user.getLogin()) != null) {
            throw new ValidationException("Login is already in use");
        }

        if (password == null || password.isEmpty()) {
            throw new ValidationException("Password is required");
        }
        if (password.length() < 4) {
            throw new ValidationException("Password can't be shorter than 4");
        }
        if (password.length() > 32) {
            throw new ValidationException("Password can't be longer than 32");
        }
        if (!password.equals(passwordConfirmation)) {
            throw new ValidationException("Passwords do not match");
        }
//        if (!isEMail(eMail)) {
//            throw new ValidationException("Wrong E-mail format");
//        }
    }

    public void validateEnter(User user, String password) throws EnterException {
        if (userRepository.findByLogin(user.getLogin()) == null){
            throw new EnterException("No such login");
        }
        if (!userRepository.findByLogin(user.getLogin()).getPasswordSha1().equals(Hashing.sha256().hashString(USER_PASSWORD_SALT + password,
                StandardCharsets.UTF_8).toString())){
            throw new EnterException("Wrong password");
        }
    }

    public void register(User user, String password) {
        user.setPasswordSha1(Hashing.sha256().hashString(USER_PASSWORD_SALT + password,
                StandardCharsets.UTF_8).toString());
        userRepository.save(user);
    }

    public static boolean isEMail(String eMail) {
        return eMail.split("@").length <= 2;
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByLogin(String userName){ return userRepository.findByLogin(userName);}

    public int findCount() {
        return userRepository.findCount();
    }

    public String find(long id) {
        return userRepository.find(id);
    }


    public List<String> getEmails() {
        List<String> temp = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            temp.add(user.getEmail());
        }
        return temp;
    }
}
