package edu.devernul.project.dao;

import edu.devernul.project.model.Status;

import java.util.List;


public interface DaoStatus {
    Status getById(Integer id);

    Status getByName(String name);

    List<Status> findAll();

    Status create(Status entity);

    Status update(Status entity);

    void delete(Status entity);
    void initAction();
}
