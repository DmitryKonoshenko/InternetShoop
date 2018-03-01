package com.divanxan.internetshop.dto;

import com.divanxan.internetshop.dao.CategoryDao;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

    @Test
    public void testAddCategory(){

        category = new Category();
        category.setName("Пылесосы");
        category.setDescription("Это пылесосы");
        category.setImageURL("CAT_2.png");

        assertEquals("Successfully added a category inside the table!", true, categoryDao.add(category));
        assertEquals("Successfully delete a category from the table!", true, categoryDao.deleteForTest(category));

    }

    @Test
    public void testGetCategory() {
        category = categoryDao.get(1);

        assertEquals("Successfully fetched a single category by id from table!",
                "Телевизоры", category.getName());
    }


        @Test
    public void testUpdateCategory() {
        category = categoryDao.get(1);
        category.setName("TV");

       assertEquals("Successfully update in table!",
               true, categoryDao.update(category));

            category.setName("Телевизоры");

            assertEquals("Successfully update in table!",
                    true, categoryDao.update(category));

        }

    @Test
    public void testDeleteCategory() {
        category = categoryDao.get(1);
        assertEquals("Successfully update in table!",
                true, categoryDao.delete(category));
        assertEquals("Successfully update in table!",
                true, categoryDao.setActive(category));
    }

//    @Test
//    public void testListCategory() {
//        assertEquals("Successfully fetched the list of categories from table!",
//                1,categoryDao.list().size());
//    }

    @Test
    public void testCRUDCAtegory() {
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

        assertEquals("Successfully update in table!", true, categoryDao.update(category));

        //delete
        category = categoryDao.get(2);
        assertEquals("Successfully update in table!", true, categoryDao.delete(category));

        //List
        assertEquals("Successfully fetched the list of categories from table!",
                4, categoryDao.list().size());

    }

    @AfterClass
    public static void reinit() {
        Category category;
        category = categoryDao.get(2);
        category.setName("Пылесосы");
        category.setActive(true);
        categoryDao.update(category);
        category = categoryDao.get(5);
        categoryDao.deleteForTest(category);
        category = categoryDao.get(6);
        categoryDao.deleteForTest(category);
    }
}