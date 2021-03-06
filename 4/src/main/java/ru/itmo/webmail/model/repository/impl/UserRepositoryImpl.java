package ru.itmo.webmail.model.repository.impl;

import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.repository.UserRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private static final File tmpDir = new File(System.getProperty("java.io.tmpdir"));

    private List<User> users;

    public UserRepositoryImpl() {
        try {
            //noinspection unchecked
            users = (List<User>) new ObjectInputStream(
                    new FileInputStream(new File(tmpDir, getClass().getSimpleName()))).readObject();
        } catch (Exception e) {
            users = new ArrayList<>();
        }
    }

    @Override
    public void save(User user) {
        if (users.isEmpty()){
            user.setId(1);
        }
        else {
            user.setId(users.get(users.size() - 1).getId() + 1);
        }

        users.add(user);

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(new File(tmpDir, getClass().getSimpleName())));
            objectOutputStream.writeObject(users);
            objectOutputStream.close();
        } catch (Exception e) {
            throw new RuntimeException("Can't save user.", e);
        }
    }

    @Override
    public User findByLogin(String login) {
        return users.stream().filter(user -> user.getLogin().equals(login)).findFirst().orElse(null);
    }

    @Override
    public int findCount() {
        return findAll().size();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public String find(long id) {
        for (User u : users) {
            if (u.getId() == id) {
                return u.getLogin();
            }
        }
        return "undefined";
    }
}
