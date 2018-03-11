package team.uptech.max.oliinyk.controllers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team.uptech.max.oliinyk.model.Category;
import team.uptech.max.oliinyk.model.Product;
import team.uptech.max.oliinyk.service.CategoryService;
import team.uptech.max.oliinyk.service.ProductService;

@RestController
public class TestController {

	@Autowired
	private CategoryService<Category> categoryService;

	@Autowired
	private ProductService<Product> productService;

	@GetMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> createCustomer() {
		Category category = new Category("vodka", "vodka");
		Product product = new Product("vodka", 12, "finlandia");
		product.setCategories(category);
		productService.save(product);

		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> getOneCategory() {
		Category category = categoryService.findEntityById(8);
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}

	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Category>> getAll() {
		List<Category> category = categoryService.findAll();
		return new ResponseEntity<List<Category>>(category, HttpStatus.OK);
	}

	@GetMapping(value = "/getjson", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getCategory() {
		Category category = categoryService.findEntityById(8);
		JSONObject json = new JSONObject();
		json.put("name", category.getName());
		json.put("descriprion", category.getDescription());
		json.put("total", category.getProducts().stream().count());
		return json.toString();
	}

	@GetMapping(value = "/getalljson", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getAllCategory() {
		List<Category> cat = categoryService.findAll();
		List<String> str = new ArrayList<>();
		for (Category category : cat) {
			JSONObject json = new JSONObject();
			json.put("name", category.getName());
			json.put("descriprion", category.getDescription());
			json.put("total", category.getProducts().stream().count());
			str.add(json.toString());
			System.out.println(json.toString());
		}
		return new ResponseEntity<List<String>>(str, HttpStatus.OK);
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
