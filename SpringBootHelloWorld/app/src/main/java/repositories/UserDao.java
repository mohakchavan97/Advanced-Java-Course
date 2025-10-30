/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositories;

import entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mohak Chavan
 */
@Repository
public interface UserDao extends CrudRepository<User, Long> {

    List<User> findByName(String name);
    
    @Query(value = "select u from User u where u.name like %?1%", nativeQuery = false)
    List<User> findByNameLike(String name);
    
    List<User> findByNameContaining(String name);

}
