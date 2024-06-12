package com.cydeo.service;

import com.cydeo.dto.UserDTO;

import java.util.List;

public interface CrudService<T,ID> {

    T save(T obj);
    T findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
    void update(T obj);


}

