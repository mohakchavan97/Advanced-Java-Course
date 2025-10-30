/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mohakchavan.mavensql.dao;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Mohak Chavan
 */
public interface BaseDao<T> {

    //region Base Methods
    void save(T t);

    List<T> getAll();

    Optional<T> findById(int id);

    void update(T t);

    void delete(T t);
    //endregion

}
