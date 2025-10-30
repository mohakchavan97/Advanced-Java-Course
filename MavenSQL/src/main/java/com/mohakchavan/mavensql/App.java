package com.mohakchavan.mavensql;

import com.mohakchavan.mavensql.dao.UserDao;
import com.mohakchavan.mavensql.impl.UserDaoImpl;
import com.mohakchavan.mavensql.model.User;
import java.util.Optional;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {

        Properties props = Profile.getProperties("db");
        Database db = Database.getInstance();

        try {
            db.connect(props);
        } catch (Exception ex) {
            System.out.println("Cannot Connect to database.");
            ex.printStackTrace();
            return;
        }

        System.out.println("Connected to Database.");
        UserDao userDao = new UserDaoImpl();
//        userDao.save(new User("Mohak"));
//        userDao.save(new User("Mohak1"));
//        userDao.save(new User("Mohak2"));
//        userDao.save(new User("Mohak3"));
//        userDao.save(new User("Mohak4"));

        var allUsers = userDao.getAll();
        allUsers.forEach(System.out::println);

        Optional<User> userOpt = userDao.findById(2);
        if (userOpt.isPresent()) {
            User u = userOpt.get();
            System.out.println("Retrieved: " + u);

            u.setName("Name Changed");
            userDao.update(u);

        } else {
            System.out.println("User not retrieved.");
        }

        userDao.delete(new User(5, ""));

        try {
            db.close();
        } catch (Exception ex) {
            System.out.println("Cannot close the connection to database.");
            ex.printStackTrace();
        }
    }
}
