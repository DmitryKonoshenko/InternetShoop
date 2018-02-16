package com.divanxan.internetshop.dto;

import com.divanxan.internetshop.dao.CategoryDao;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;

public class CategoryTest {

    private static AnnotationConfigApplicationContext context;

    private static CategoryDao categoryDao;

    private Category category;

    @BeforeClass
    public static void init() {
        context = new AnnotationConfigApplicationContext();
        context.scan("com.divanxan.internetshop");
        context.refresh();
        categoryDao = (CategoryDao) context.getBean("categoryDao");
    }

//    @Test
//    public void testAddCategory(){
//
//        category = new Category();
//        category.setName("Пылесосы");
//        category.setDescription("Это пылесосы");
//        category.setImageURL("CAT_2.png");
//
//        assertEquals("Successfully added a category inside the table!", true, categoryDao.add(category));
//
//    }

//    @Test
//    public void testGetCategory() {
//        category = categoryDao.get(1);
//
//        assertEquals("Successfully fetched a single category by id from table!",
//                "Телевизоры", category.getName());
//    }


//        @Test
//    public void testUpdateCategory() {
//        category = categoryDao.get(1);
//        category.setName("TV");
//
//       assertEquals("Successfully update in table!",
//               true, categoryDao.update(category));
//    }

//    @Test
//    public void testDeleteCategory() {
//        category = categoryDao.get(1);
//        assertEquals("Successfully update in table!",
//                true, categoryDao.delete(category));
//    }

//    @Test
//    public void testListCategory() {
//        assertEquals("Successfully fetched the list of categories from table!",
//                1,categoryDao.list().size());
//    }

    @Test
    public  void testCRUDCAtegory(){
        //add operation
        category = new Category();
        category.setName("Телевизоры");
        category.setDescription("Это телевизоры");
        category.setImageURL("CAT_1.png");

        assertEquals("Successfully added a category inside the table!", true, categoryDao.add(category));

        category = new Category();
        category.setName("Пылесосы");
        category.setDescription("Это пылесосы");
        category.setImageURL("CAT_2.png");

        assertEquals("Successfully added a category inside the table!", true, categoryDao.add(category));

        //fetching and updating
        category = categoryDao.get(2);
        category.setName("Пылька");

        assertEquals("Successfully update in table!",true, categoryDao.update(category));

        //delete
        category = categoryDao.get(2);
        assertEquals("Successfully update in table!", true, categoryDao.delete(category));

        //List
        assertEquals("Successfully fetched the list of categories from table!",
                1,categoryDao.list().size());

    }
}