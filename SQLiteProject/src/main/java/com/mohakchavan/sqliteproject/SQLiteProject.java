/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mohakchavan.sqliteproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mohak
 */
public class SQLiteProject {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        try {
            int[] ids = {0, 1, 2};
            String[] names = {"mohak", "mohak1", "mohak2"};

            Class.forName("com.mysql.cj.jdbc.Driver");

            String dbUrl = "jdbc:mysql://localhost:3306/people";
            System.out.println();
            Connection conn = DriverManager.getConnection(dbUrl,"root","Moh@k11");
            conn.setAutoCommit(false);

            Statement statement = conn.createStatement();
            String sql = "";
//            sql = "CREATE TABLE IF NOT EXISTS Users (id integer primary key, name text not null, surname text default null)";
//            statement.execute(sql);

            sql = "INSERT INTO Users (name) values (?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            for (int i = 0; i < names.length; i++) {
                ps.setString(1, names[i]);
                
                ps.executeUpdate();
            }
            conn.commit();
            ps.close();

            sql = "SELECT * FROM Users";
            ResultSet rs = statement.executeQuery(sql);

            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String sur = rs.getString("surname");
                    System.out.println(id + ":" + name + ":" + sur);
                }
                rs.close();
            }

//            sql = "DROP TABLE IF EXISTS Users";
//            statement.execute(sql);
            conn.commit();
            statement.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

    }
}
