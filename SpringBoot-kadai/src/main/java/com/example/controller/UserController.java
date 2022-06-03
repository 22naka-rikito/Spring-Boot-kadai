package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.controller.form.IndexForm;
import com.example.entity.Product;
import com.example.entity.User;
import com.example.service.ProductService;
import com.example.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	ProductService productService;
	@Autowired
	HttpSession session;
	
	@RequestMapping(value = {"/","/index"})
	public String index(@ModelAttribute("index") IndexForm form, Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/menu", params = "login", method=RequestMethod.POST)
	public String login(@Validated @ModelAttribute("index") IndexForm form, BindingResult bindingResult, Model model){
		if (bindingResult.hasErrors()) {
            return "index";
        }
		User user = userService.findUser(form);
		if(user != null) {
			List<Product> productList = productService.findProduct("");
			session.setAttribute("user", user);
			model.addAttribute("findList", productList);
			model.addAttribute("findSize", "検索結果:" + productList.size() + "件");
			return "menu";
		}else {
			model.addAttribute("msg", "IDまたはパスワードが不正です");
			return "index";
		}
	}
	
	@RequestMapping(value = "/menu", method=RequestMethod.GET)
	public String loginGet(@Validated @ModelAttribute("index") IndexForm form, BindingResult bindingResult, Model model){
		if(session.getAttribute("user") != null) {
			List<Product> productList = productService.findProduct("");
			model.addAttribute("findList", productList);
			model.addAttribute("findSize", "検索結果:" + productList.size() + "件");
			return "menu";
		}
		if (bindingResult.hasErrors()) {
            return "index";
        }
		User user = userService.findUser(form);
		if(user != null) {
			List<Product> productList = productService.findProduct("");
			session.setAttribute("user", user);
			model.addAttribute("findList", productList);
			model.addAttribute("findSize", "検索結果:" + productList.size() + "件");
			return "menu";
		}else {
			model.addAttribute("msg", "IDまたはパスワードが不正です");
			return "index";
		}
	}
}