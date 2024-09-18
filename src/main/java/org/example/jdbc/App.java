package org.example.jdbc;

import lombok.AllArgsConstructor;
import org.example.jdbc.configuration.DIConfiguration;
import org.example.jdbc.dao.UserDAO;
import org.example.jdbc.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/13/2024
 */
@Component
@AllArgsConstructor
public class App {

    private final UserDAO userDAO;

    public void createModels(){
        userDAO.saveOrUpdate(new User(1, "test1", "test@gmail.com", LocalDateTime.now()));
        userDAO.saveOrUpdate(new User(2, "test2", "test@gmail.com", LocalDateTime.now()));
        userDAO.saveOrUpdate(new User(3, "test3", "test@gmail.com", LocalDateTime.now()));
    }

    public void listModel(){
        System.err.println(userDAO.list(10000, 0 ));
    }

    public static void main( String[] args )
    {
        long start = System.currentTimeMillis();
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DIConfiguration.class);
        App app = context.getBean(App.class);
        app.listModel();
        System.err.println(System.currentTimeMillis() - start + "ms");
    }

}
