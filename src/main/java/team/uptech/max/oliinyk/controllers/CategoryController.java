package team.uptech.max.oliinyk.controllers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
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
import team.uptech.max.oliinyk.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService<Category> categoryService;

	@PostMapping(value = "/findcategory", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findCategoryById(@RequestParam("id") long id) {
		Category category = categoryService.findEntityById(id);
		if (category != null) {
			return new ResponseEntity<String>(
					getJSON(category.getName(), category.getDescription(), category.getProducts().stream().count()),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Wrong " + id, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/addcategory", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createCategory(@RequestParam(value = "name") String name,
			@RequestParam(value = "description") String description) {
		Category category = new Category();
		category.setName(name);
		category.setDescription(description);
		categoryService.save(category);
		return new ResponseEntity<String>(getJSON(category.getName(), category.getDescription(), 0), HttpStatus.OK);
	}

	// @DeleteMapping
	@GetMapping(value = "/deletecategory/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> deleteCategory(@PathVariable(value = "id") long id) {
		categoryService.delete(id);
		return new ResponseEntity<Long>(id, HttpStatus.OK);

	}

	@GetMapping(value = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getAllCategory() {
		List<Category> cat = categoryService.findAll();
		List<String> str = new ArrayList<>();
		for (Category category : cat) {
			str.add(getJSON(category.getName(), category.getDescription(), category.getProducts().stream().count()));
		}
		return new ResponseEntity<List<String>>(str, HttpStatus.OK);
	}

	// @PutMapping
	@GetMapping(value = "/updatecategory/{id}/{description}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateCategory(@PathVariable(value = "id") long id,
			@PathVariable String description) {
		Category category = categoryService.findEntityById(id);
		if (category == null) {
			return new ResponseEntity<String>("No entity with id =" + id, HttpStatus.NOT_FOUND);
		}
		category.setDescription(description);
		categoryService.update(category);
		return new ResponseEntity<String>(
				getJSON(category.getName(), category.getDescription(), category.getProducts().stream().count()),
				HttpStatus.OK);

	}

	@GetMapping(value = "/findcategorybyname/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> findCategoryByName(@PathVariable(value = "name") String name) {
		List<Category> cat = categoryService.getCategoriesByName(name);
		List<String> str = new ArrayList<>();
		if (cat != null) {
			for (Category category : cat) {
				str.add(getJSON(category.getName(), category.getDescription(),
						category.getProducts().stream().count()));
			}
			return new ResponseEntity<List<String>>(str, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<String>>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/findcategorybydesc/{description}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> findCategoryByDesc(@PathVariable(value = "description") String description) {
		List<Category> cat = categoryService.getCategoriesByDescription(description);
		List<String> str = new ArrayList<>();
		if (cat != null) {
			for (Category category : cat) {
				str.add(getJSON(category.getName(), category.getDescription(),
						category.getProducts().stream().count()));
			}
			return new ResponseEntity<List<String>>(str, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<String>>(HttpStatus.NOT_FOUND);
		}
	}

	private final String getJSON(String name, String description, long total) {
		JSONObject json = new JSONObject();
		json.put("name", name);
		json.put("descriprion", description);
		json.put("total", total);
		return json.toString();
	}

}