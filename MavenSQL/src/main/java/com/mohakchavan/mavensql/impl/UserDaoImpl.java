/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mohakchavan.mavensql.impl;

import com.mohakchavan.mavensql.DaoException;
import com.mohakchavan.mavensql.Database;
import com.mohakchavan.mavensql.dao.UserDao;
import com.mohakchavan.mavensql.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohak Chavan
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void save(User t) {

        try {
            Connection c = Database.getInstance().getConnection();

            PreparedStatement s = c.prepareStatement("INSERT INTO " + TABLE_USERS + " (" + USERS_NAME + ") VALUES (?)");
            s.setString(1, t.getName());

            s.executeUpdate();
            s.close();

        } catch (SQLException ex) {
            throw new DaoException(ex);
        }

    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = new ArrayList<>();
        try {
            Connection c = Database.getInstance().getConnection();
            ResultSet rs = c.createStatement().executeQuery("SELECT " + USERS_ID + ", " + USERS_NAME + " FROM " + TABLE_USERS + " ORDER BY " + USERS_ID);

            while (rs.next()) {
                int id = rs.getInt(USERS_ID);
                String name = rs.getString(USERS_NAME);
                allUsers.add(new User(id, name));
            }
        } catch (SQLException ex) {
            allUsers = new ArrayList<>();
            throw new DaoException(ex);
        }
        return allUsers;
    }

    @Override
    public Optional<User> findById(int id) {
        Optional<User> returnedUser = Optional.empty();
        try {
            Connection c = Database.getInstance().getConnection();

            PreparedStatement s = c.prepareStatement("SELECT " + USERS_NAME + " FROM " + TABLE_USERS + " WHERE " + USERS_ID + " = ?");
            s.setInt(1, id);

            ResultSet rs = s.executeQuery();

            if (rs.next()) {
                User user = new User(id, rs.getString(USERS_NAME));
                returnedUser = Optional.of(user);
            }

            s.close();

        } catch (SQLException ex) {
            returnedUser = Optional.empty();
            throw new DaoException(ex);
        }
        return returnedUser;
    }

    @Override
    public void update(User t) {
        try {
            Connection c = Database.getInstance().getConnection();

            PreparedStatement ps = c.prepareStatement("UPDATE " + TABLE_USERS + " SET " + USERS_NAME + " = ? WHERE " + USERS_ID + " = ?");
            ps.setString(1, t.getName());
            ps.setInt(2, t.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(User t) {
        try {
            Connection c = Database.getInstance().getConnection();

            PreparedStatement ps = c.prepareStatement("DELETE FROM " + TABLE_USERS + " WHERE " + USERS_ID + " = ?");
            ps.setInt(1, t.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new DaoException(ex);
        }

    }

}
