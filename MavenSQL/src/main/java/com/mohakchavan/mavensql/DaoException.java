/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mohakchavan.mavensql;

import java.sql.SQLException;

/**
 *
 * @author Mohak Chavan
 */
public class DaoException extends RuntimeException {

    public DaoException(SQLException ex) {
        super(ex);
        ex.printStackTrace();
    }
}
