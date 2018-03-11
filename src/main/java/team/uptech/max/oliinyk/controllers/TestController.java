package team.uptech.max.oliinyk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team.uptech.max.oliinyk.model.Category;
import team.uptech.max.oliinyk.model.Product;
import team.uptech.max.oliinyk.service.GeneralService;

@RestController
public class TestController {

	@Autowired
	private GeneralService<Category> categoryService;

	@Autowired
	private GeneralService<Product> productService;

	@RequestMapping(value = "/test")
	String home() {
		return "My Test Spring Boot!";
	}

	@RequestMapping(value = "/add")
	String add() {

		Product product = new Product("vodka", 12, "finlandia");
		productService.save(product);
		Product product2 = new Product("vodka", 13, "nemiroff");
		productService.save(product2);
		Product product3 = new Product("vodka", 13, "absolut");
		productService.save(product3);

		return "Product added!";
	}

	@RequestMapping(value = "/addcat")
	String addCat() {
		Category category = new Category("vodka", "vodka");
		categoryService.save(category);
		return "Category added!";
	}

	@RequestMapping(value = "/delete")
	String delete() {
		productService.delete(4);
		return "Product deleted!";
	}

	@RequestMapping(value = "/list")
	String list() {

		return productService.findAll().toString();
	}

}
