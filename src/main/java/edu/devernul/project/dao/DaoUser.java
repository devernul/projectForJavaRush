package edu.devernul.project.dao;

import edu.devernul.project.model.User;

import java.util.List;

public interface DaoUser {

    User getById(Integer id);

    User getByName(String name);

    List<User> findAll();

    User create(User entity);

    User update(User entity);

    void delete(User entity);

    void initAction();

}
