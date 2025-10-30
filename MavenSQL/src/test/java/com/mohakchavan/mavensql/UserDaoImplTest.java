/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mohakchavan.mavensql;

import com.mohakchavan.mavensql.dao.UserDao;
import com.mohakchavan.mavensql.impl.UserDaoImpl;
import com.mohakchavan.mavensql.model.User;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mohak Chavan
 */
public class UserDaoImplTest {

    private Connection conn;
    private ArrayList<User> users;
    public static final int NUM_TEST_USERS = 5;

    public UserDaoImplTest() {
    }

    private List<User> loadUsers() throws IOException {

        return Files.lines(Path.of("usernames.txt"))
                .filter(word -> word.length() > 3 && word.length() < 20)
                .limit(NUM_TEST_USERS)
                .map(word -> new User(word))
                .collect(Collectors.toList());

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws SQLException, IOException {

        users = (ArrayList<User>) loadUsers();

        var props = Profile.getProperties("db");
        var db = Database.getInstance();
        db.connect(props);
        conn = db.getConnection();
        conn.setAutoCommit(false);
    }

    @After
    public void tearDown() throws SQLException {
        Database.getInstance().close();
    }

    private int getMaxId() throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("select max(id) as id from users");
        rs.next();
        int maxId = rs.getInt("id");
        rs.close();
        return maxId;
    }

    private List<User> getUsersInRange(int minId, int maxId) throws SQLException {
        ArrayList<User> retrieved = new ArrayList<>();
        PreparedStatement st = conn.prepareStatement("select id,name from users where id >= ? and id <= ?");
        st.setInt(1, minId);
        st.setInt(2, maxId);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            retrieved.add(new User(rs.getInt("id"), rs.getString("name")));
        }
        return retrieved;
    }

    @Test
    public void testSaveMultiple() throws SQLException {
        System.out.println("com.mohakchavan.mavensql.UserDaoImplTest.testSaveMultiple()");
        UserDao dao = new UserDaoImpl();

        for (User u : users) {
            dao.save(u);
        }

        int maxId = getMaxId();
        int startingIndex = maxId - NUM_TEST_USERS;
        for (int i = 0; i < users.size(); i++) {
            int idToSet = startingIndex + i + 1;
            users.get(i).setId(idToSet);
        }
        List<User> retrieved = getUsersInRange(startingIndex + 1, maxId);

        assertEquals("Retrieved users size is not equal to number of test users.", retrieved.size(), NUM_TEST_USERS);
        assertEquals("Retrieved users does not match saved users.", users, retrieved);
    }

    @Test
    public void testDelete() throws SQLException {
        System.out.println("com.mohakchavan.mavensql.UserDaoImplTest.testDelete()");
        UserDao dao = new UserDaoImpl();

        for (User u : users) {
            dao.save(u);
        }

        int maxId = getMaxId();
        int startingIndex = maxId - NUM_TEST_USERS;
        for (int i = 0; i < users.size(); i++) {
            int idToSet = startingIndex + i + 1;
            users.get(i).setId(idToSet);
        }
        
        int toDeleteIndex = NUM_TEST_USERS / 2;
        User toDeleteUser = users.get(toDeleteIndex);

        users.remove(toDeleteUser);
        
        dao.delete(toDeleteUser);
        
        List<User> retrieved = getUsersInRange(startingIndex + 1, maxId);

        assertEquals("Retrieved users size is not equal to number of test users.", retrieved.size(), users.size());
        assertEquals("Retrieved users does not match saved users.", users, retrieved);
    }

    @Test
    public void testGetAll() throws SQLException {
        System.out.println("com.mohakchavan.mavensql.UserDaoImplTest.testGetAll()");
        UserDao dao = new UserDaoImpl();

        for (User u : users) {
            dao.save(u);
        }

        int maxId = getMaxId();
        int startingIndex = maxId - users.size();
        for (int i = 0; i < users.size(); i++) {
            int idToSet = startingIndex + i + 1;
            users.get(i).setId(idToSet);
        }
        List<User> dbUsers = dao.getAll();
        dbUsers = dbUsers.subList(dbUsers.size() - users.size(), dbUsers.size());

        assertEquals("Retrieved users size is not equal to number of test users.", dbUsers.size(), NUM_TEST_USERS);
        assertEquals("Retrieved users does not match saved users.", users, dbUsers);
    }

    @Test
    public void testFindAndUpdate() throws SQLException {
        System.out.println("com.mohakchavan.mavensql.UserDaoImplTest.testFindAndUpdate()");
        User user = users.get(0);

        UserDao dao = new UserDaoImpl();
        dao.save(user);

        int maxId = getMaxId();
        user.setId(maxId);

        Optional<User> retrievedUserOpt = dao.findById(maxId);

        assertTrue("User not retrieved.", retrievedUserOpt.isPresent());

        User retrievedUser = retrievedUserOpt.get();

        assertEquals("Retrieved user does not match with saved user.", user, retrievedUser);

        user.setName("abcdefxyz");

        dao.update(user);

        retrievedUserOpt = dao.findById(maxId);

        assertTrue("User not retrieved after updating.", retrievedUserOpt.isPresent());

        retrievedUser = retrievedUserOpt.get();

        assertEquals("Retrieved user does not match with updated user.", user, retrievedUser);
        System.out.println(retrievedUser);
    }

    @Test
    public void testSave() throws SQLException {
        System.out.println("com.mohakchavan.mavensql.UserDaoImplTest.testSave()");
        User user = new User("Jupiter Test");

        UserDao dao = new UserDaoImpl();

        dao.save(user);

        ResultSet rs = conn.createStatement().executeQuery("select id,name from users order by id desc");

        assertTrue("Cannot Retrieve inserted User", rs.next());

        String name = rs.getString("name");
        assertEquals("Name does not matches with retrieved name.", user.getName(), name);
        rs.close();
    }

}
