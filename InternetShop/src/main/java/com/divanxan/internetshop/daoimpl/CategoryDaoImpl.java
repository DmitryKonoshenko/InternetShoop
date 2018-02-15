package com.divanxan.internetshop.daoimpl;

import com.divanxan.internetshop.dao.CategoryDao;
import com.divanxan.internetshop.dto.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("categoryDao")
public class CategoryDaoImpl implements CategoryDao {

    private static List<Category> categories =  new ArrayList<>();

    static{
        Category category1 = new Category();

        category1.setId(1);
        category1.setName("Televizion");
        category1.setDescription("This is tc set");
        category1.setImageURL("CAT_1.png");

        categories.add(category1);

        Category category2 = new Category();

        category2.setId(2);
        category2.setName("Mobile");
        category2.setDescription("This is mobile");
        category2.setImageURL("CAT_2.png");

        categories.add(category1);

        Category category3 = new Category();

        category3.setId(3);
        category3.setName("pili");
        category3.setDescription("This is pili");
        category3.setImageURL("CAT_3.png");

        categories.add(category3);
    }

    @Override
    public List<Category> list() {
        return null;
    }
}
