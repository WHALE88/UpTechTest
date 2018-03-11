package team.uptech.max.oliinyk.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import team.uptech.max.oliinyk.model.Category;
import team.uptech.max.oliinyk.model.Product;
import team.uptech.max.oliinyk.service.CategoryService;
import team.uptech.max.oliinyk.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private CategoryService<Category> categoryService;

	@Autowired
	private ProductService<Product> productService;

	@PostMapping(value = "/findproduct", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> findProductById(@RequestParam("id") long id) {
		Product product = productService.findEntityById(id);
		if (product != null) {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} else {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}

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

	// @DeleteMapping
	@GetMapping(value = "/deleteproduct/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> deleteProduct(@PathVariable(value = "id") long id) {
		productService.delete(id);
		return new ResponseEntity<Long>(id, HttpStatus.OK);

	}

	// @PutMapping
	@GetMapping(value = "/updateproduct/{id}/{name}/{price}/{description}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> updateProduct(@PathVariable(value = "id") long id,
			@PathVariable(value = "name") String name, @PathVariable Integer price, @PathVariable String description) {
		Product product = productService.findEntityById(id);
		product.setName(name);
		product.setPrice(price);
		product.setDescription(description);
		productService.update(product);
		return new ResponseEntity<Long>(id, HttpStatus.OK);

	}

	@PostMapping(value = "/getbycatname", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getPtodByCatname(@RequestParam(value = "category") String name) {
		return new ResponseEntity<List<Product>>(productService.getProdByCategoryName(name), HttpStatus.OK);
	}

	@PostMapping(value = "/getbycatdesc", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getPtodByCatDesc(@RequestParam(value = "category") String description) {
		return new ResponseEntity<List<Product>>(productService.getProdByCategoryDesc(description), HttpStatus.OK);

	}

}
