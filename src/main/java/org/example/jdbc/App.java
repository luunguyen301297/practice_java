package org.example.jdbc;

import lombok.AllArgsConstructor;
import org.example.jdbc.configuration.DIConfiguration;
import org.example.jdbc.dao.ModelDAO;
import org.example.jdbc.model.Model;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/13/2024
 */
@Component
@AllArgsConstructor
public class App {

    private final ModelDAO modelDAO;

    public List<Model> createModels(){
        modelDAO.saveOrUpdate(new Model(1, "test1", "test@gmail.com"));
        modelDAO.saveOrUpdate(new Model(2, "test2", "test@gmail.com"));
        modelDAO.saveOrUpdate(new Model(3, "test3", "test@gmail.com"));
        return modelDAO.list();
    }

    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DIConfiguration.class);
        App app = context.getBean(App.class);

        List<Model> models = app.createModels();
        models.forEach(System.err::println);
    }

}
