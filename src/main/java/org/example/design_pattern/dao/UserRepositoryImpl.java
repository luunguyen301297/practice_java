package org.example.design_pattern.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements Dao<UserEntity> {

    List<UserEntity> users;

    public UserRepositoryImpl() {
        users = new ArrayList<>();
        users.add(new UserEntity(1, "Putin", "putin@kremlin.com"));
        users.add(new UserEntity(2, "Trump", "trump@whitehouse.com"));
    }

    @Override
    public List<UserEntity> findAll() {
        return users;
    }

    @Override
    public Optional<UserEntity> findById(int id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst();
    }

    @Override
    public void save(UserEntity user) {
        users.add(user);
    }

    @Override
    public void update(UserEntity user) {
        this.findById(user.getId()).ifPresent(u -> {
            u.setName(user.getName());
            u.setEmail(user.getEmail());
        });
    }

    @Override
    public void delete(UserEntity user) {
        users.remove(user);
    }

}
