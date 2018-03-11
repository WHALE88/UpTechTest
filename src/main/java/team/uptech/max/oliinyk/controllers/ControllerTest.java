package team.uptech.max.oliinyk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ControllerTest {

	@RequestMapping(value = "/tes", method = RequestMethod.GET)
	public String firstTest(ModelMap model) {
		model.addAttribute("mymessage", "It's Test");
		return "mytest";
	}
}
