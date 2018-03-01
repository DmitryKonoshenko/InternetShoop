package com.divanxan.internetshop.dto;

import com.divanxan.internetshop.dao.ProductDao;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


public class ProductTest {

    private static AnnotationConfigApplicationContext context;


    private static ProductDao productDao;


    private Product product;


    @BeforeClass
    public static void init() {
        context = new AnnotationConfigApplicationContext();
        context.scan("com.divanxan.internetshop");
        context.refresh();
        productDao = (ProductDao)context.getBean("productDao");
    }

	@Test
	public void testCRUDProduct() {
		product = new Product();

		product.setName("PHD 2247Ti");
		product.setBrand("Polaris");
		product.setDescription("Элегантный и стильный Polaris PHD 2247Ti с тремя температурными режимами, двумя мощностями обдува и функцией «Холодный воздух» сделает ваши волосы послушными, ровными и шелковистыми.");
		product.setUnitPrice(1310);
		product.setActive(true);
		product.setQuantity(5);
		product.setCategoryId(3);
		product.setSupplierId(4);

		assertEquals("Something went wrong while inserting a new product!",
				true,productDao.add(product));

        assertEquals("Something went wrong while deleting the existing record!",
                true,productDao.deleteForTest(product));


		product = productDao.get(2);
		product.setName("32LJ519U");
		assertEquals("Something went wrong while updating the existing record!",
				true,productDao.update(product));

		assertEquals("Something went wrong while deleting the existing record!",
				true,productDao.delete(product));


		assertEquals("Something went wrong while fetching the list of products!",
				9,productDao.list().size());

        assertEquals("Something went wrong while deleting the existing record!",
                true,productDao.setActiv(product));
	}

    @Test
    public void testListActiveProducts(){
        assertEquals("Something went wrong while fetching the list of products!",
				9,productDao.listActiveProducts().size());
    }

    @Test
    public void testListProductsByCategory(){
        assertEquals("Something went wrong while fetching the list of products!",
                3,productDao.listActiveProductsByCategory(3).size());
        assertEquals("Something went wrong while fetching the list of products!",
                3,productDao.listActiveProductsByCategory(2).size());
        assertEquals("Something went wrong while fetching the list of products!",
                3,productDao.listActiveProductsByCategory(1).size());
    }

    @Test
    public void testGetLatestActiveProducts(){
        assertEquals("Something went wrong while fetching the list of products!",
                9,productDao.getLatestActiveProducts(10)
                        .size());
    }


}