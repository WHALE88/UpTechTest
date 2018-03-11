package team.uptech.max.oliinyk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import team.uptech.max.oliinyk.model.Category;
import team.uptech.max.oliinyk.model.Product;
import team.uptech.max.oliinyk.service.GeneralService;

@Controller
public class VievController {

	@Autowired
	private GeneralService<Category> categoryService;

	@Autowired
	private GeneralService<Product> productService;

	@GetMapping(value = "/addproduct")
	public String getAddProductView(ModelMap model) {
		model.addAttribute("category", categoryService.findAll());
		return "addproduct";
	}
}