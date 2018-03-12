package team.uptech.max.oliinyk.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import team.uptech.max.oliinyk.controllers.ProductController;
import team.uptech.max.oliinyk.model.Category;
import team.uptech.max.oliinyk.model.Product;
import team.uptech.max.oliinyk.service.CategoryService;
import team.uptech.max.oliinyk.service.ProductService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ProductTest {

	@Mock
	private CategoryService<Category> categoryService;

	@Mock
	private ProductService<Product> productService;

	@InjectMocks
	ProductController sut;

	@Test
	public void getAllProducts() throws Exception {
		List<Product> product = sut.getAllProduct();
		Mockito.verify(productService).findAll();
	}

	@Test
	public void getPtodByCatDesc() throws Exception {
		ResponseEntity<List<Product>> prod = sut.getPtodByCatDesc("");
		Mockito.verify(productService).getProdByCategoryDesc("");
		Assert.assertNotNull("failure - expected not null", prod);
	}

}
