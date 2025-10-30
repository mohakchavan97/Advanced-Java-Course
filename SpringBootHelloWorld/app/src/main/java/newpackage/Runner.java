/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newpackage;

import entities.User;
import greeters.Greeter;
import java.util.Optional;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import repositories.UserDao;

/**
 *
 * @author Mohak Chavan
 */
@Component
public class Runner implements CommandLineRunner {

//    @Lazy
    /*@Autowired
    private Greeter greeter;*/
    @Autowired
    private UserDao userDao;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello");
//        greeter.greet();

//        userDao.save(new User("morpheus", "morpheus@sdfsd.com"));
        /*userDao.findById(1L).ifPresentOrElse(new Consumer<User>() {
            @Override
            public void accept(User t) {
                t.setName("Pluto");
                userDao.save(t);
            }
        }, new Runnable() {
            @Override
            public void run() {

            }
        });*/
//        Optional<User> userOpt = userDao.findById(1L);
//        if (userOpt.isPresent()) {
//            System.out.println(userOpt.get());
//        }
        userDao.findByName("morpheus").forEach(System.out::println);
        userDao.findByNameLike("mor").forEach(System.out::println);
        userDao.findByNameContaining("mor").forEach(System.out::println);

    }

//    @Bean
//    public Greeter createGreeter() {
//        return new Greeter();
//    }
}
