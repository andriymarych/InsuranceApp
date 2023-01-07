package com.marych.insuranceApp.dao;


import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(int id);

    List<T> getAll(int holderId);

}

