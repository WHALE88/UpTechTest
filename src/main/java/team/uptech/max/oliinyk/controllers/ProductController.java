package team.uptech.max.oliinyk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import team.uptech.max.oliinyk.model.Category;
import team.uptech.max.oliinyk.model.Product;
import team.uptech.max.oliinyk.service.GeneralService;

@RestController
public class ProductController {
	@Autowired
	private GeneralService<Category> categoryService;

	@Autowired
	private GeneralService<Product> productService;

	@PostMapping(value = "/addproduct", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> createProduct(@RequestParam(value = "name") String name,
			@RequestParam(value = "price") int price, @RequestParam("description") String description,
			@RequestParam(value = "category", required = false) long categoryId) {

		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setDescription(description);
		product.setCategories(categoryService.findEntityById(categoryId));
		productService.save(product);

		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

}
