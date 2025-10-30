/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mohakchavan.mavensql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author mohak
 */
public class Database {

    private static Database db = null;
    private Connection conn;

    private Database() {
//        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    public static Database getInstance() {
        if (db == null) {
            db = new Database();
        }
        return db;
    }

    public void connect(Properties props) throws SQLException {
        String server = props.getProperty("server", "");
        String port = props.getProperty("port", "");
        String database = props.getProperty("database", "");
        String user = props.getProperty("user", "");
        String password = props.getProperty("password", "");
        
        String url = String.format("jdbc:mysql://%s:%s/%s", server, port, database);
        
        conn = DriverManager.getConnection(url, user, password);
    }

    public void close() throws SQLException {
        conn.close();
    }

    public Connection getConnection() {
        return conn;
    }

}
