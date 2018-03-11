package team.uptech.max.oliinyk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import team.uptech.max.oliinyk.model.Category;
import team.uptech.max.oliinyk.service.CategoryService;

@Controller
public class VievController {

	@Autowired
	private CategoryService<Category> categoryService;

	@GetMapping(value = "/addproduct")
	public String getAddProductView(ModelMap model) {
		model.addAttribute("category", categoryService.findAll());
		return "addproduct";
	}

	@GetMapping(value = "/findproduct")
	public String findProduct() {
		return "findproduct";
	}

	@GetMapping(value = "/addcategory")
	public String getAddCategoryView() {
		return "addcategory";
	}

	@GetMapping(value = "/findcategory")
	public String findCategory() {
		return "findcategory";
	}

	@GetMapping(value = "/getbycatnameordesc")
	public String getByCatNameOrDesc(ModelMap model) {
		model.addAttribute("category", categoryService.findAll());
		return "getbycatnameordesc";
	}

}