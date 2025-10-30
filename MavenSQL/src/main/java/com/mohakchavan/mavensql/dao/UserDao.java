/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mohakchavan.mavensql.dao;

import com.mohakchavan.mavensql.model.User;

/**
 *
 * @author Mohak Chavan
 */
public interface UserDao extends BaseDao<User> {

    public static final String TABLE_USERS = "Users";
    public static final String USERS_ID = "ID";
    public static final String USERS_NAME = "Name";

}
