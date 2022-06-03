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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.controller.form.DetailForm;
import com.example.controller.form.InsertForm;
import com.example.controller.form.UpdateForm;
import com.example.entity.Category;
import com.example.entity.Product;
import com.example.service.CategoryService;
import com.example.service.ProductService;

@Controller
public class ProductController{
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	HttpSession session;
	
	@RequestMapping(value = "/menu", params = "find")
	public String findList(@RequestParam("keyword") String keyWord,  Model model) {
		List<Product> productList = productService.findProduct(keyWord);
		model.addAttribute("findList", productList);
		model.addAttribute("findSize", "検索結果:" + productList.size() + "件");
		return "menu";
	}
	
	@RequestMapping(value = "/insert")
	public String insert(@ModelAttribute("insert") InsertForm form, Model model) {
		if(session.getAttribute("user") == null) {
			return "redirect:index";
		}
		List<Category> categoryList = categoryService.findAll();
		model.addAttribute("categoryList", categoryList);
		return "insert";
	}
	
	@RequestMapping(value = "/menu", params = "insert")
	public String insertProduct(@Validated @ModelAttribute("insert") InsertForm form, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			List<Category> categoryList = categoryService.findAll();
			model.addAttribute("categoryList", categoryList);
            return "insert";
        }
		if(productService.findByProductId(form.getProductId()) != null) {
			List<Category> categoryList = categoryService.findAll();
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("msg", "商品が重複しています");
            return "insert";
		}
		productService.insertProduct(form);
		List<Product> productList = productService.findProduct("");
		model.addAttribute("findList", productList);
		model.addAttribute("findSize", "検索結果:" + productList.size() + "件");
		return "menu";
	}
	
	@RequestMapping(value = "/menu", params="back")
	public String backMenu(Model model) {
		List<Product> productList = productService.findProduct("");
		model.addAttribute("findList", productList);
		model.addAttribute("findSize", "検索結果:" + productList.size() + "件");
		return "menu";
	}
	
	@RequestMapping(value = "/detail")
	public String productDetail(@ModelAttribute("detail") DetailForm form, @RequestParam("id") String id, Model model) {
		if(session.getAttribute("user") == null) {
			return "redirect:index";
		}
		Product product = productService.findById(Integer.parseInt(id));
		form.setProductId(product.getProductId());
		form.setProductName(product.getProductName());
		form.setPrice(product.getPrice());
		form.setCategoryName(product.getCategoryName());
		form.setDescription(product.getDescription());
		return "detail";
	}
	
	@RequestMapping(value = "/detail", params ="back")
	public String backMenu(@ModelAttribute("detail") DetailForm form, Model model) {
		List<Product> productList = productService.findProduct("");
		model.addAttribute("findList", productList);
		model.addAttribute("findSize", "検索結果:" + productList.size() + "件");
		return "menu";
	}
	
	@RequestMapping(value = "/detail", params ="delete")
	public String deleteProduct(@ModelAttribute("detail") DetailForm form, Model model) {
		productService.deleteProduct(form);
		List<Product> productList = productService.findProduct("");
		model.addAttribute("findList", productList);
		model.addAttribute("findSize", "検索結果:" + productList.size() + "件");
		return "menu";
	}
	
	@RequestMapping(value = "/detail", params ="update")
	public String edit(@ModelAttribute("detail") DetailForm form,@ModelAttribute("update") UpdateForm updateForm, Model model) {
		if(session.getAttribute("user") == null) {
			return "redirect:index";
		}
		Product product = productService.findByProductId(form.getProductId());
		updateForm.setId(product.getId());
		updateForm.setCategoryId(product.getCategoryId());
		List<Category> categoryList = categoryService.findAll();
		model.addAttribute("categoryList", categoryList);
		return "update";
	}
	
	@RequestMapping( value = "/update", params = "update")
	public String updateProduct(@Validated @ModelAttribute("update") UpdateForm form, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			List<Category> categoryList = categoryService.findAll();
			model.addAttribute("categoryList", categoryList);
            return "update";
        }
		productService.updateProduct(form.getId(), form.getProductId(), form.getProductName(), form.getPrice());
		List<Product> productList = productService.findProduct("");
		model.addAttribute("findList", productList);
		model.addAttribute("findSize", "検索結果:" + productList.size() + "件");
		return "menu";
	}
	
	@RequestMapping(value = "/update", params ="back")
	public String backMenu(@ModelAttribute("update") UpdateForm form, Model model) {
		List<Product> productList = productService.findProduct("");
		model.addAttribute("findList", productList);
		model.addAttribute("findSize", "検索結果:" + productList.size() + "件");
		return "menu";
	}
	
	@RequestMapping(value = "/sort")
	public String sort(@RequestParam("orderBy") String orderBy, Model model) {
		System.out.println("コントロール" + orderBy);
		List<Product> productList = productService.findProduct("");
		if ("id".equals(orderBy)) {
			productList.sort((p1, p2) -> p1.getId() >= p2.getId() ? 1 : -1);
		} else if ("category".equals(orderBy)) {
			productList.sort((p1, p2) -> p1.getCategoryName().compareTo(p2.getCategoryName()));
		} else if ("price_asc".equals(orderBy)) {
			productList.sort((p1, p2) -> p1.getPrice() >= p2.getPrice() ? 1 : -1);
		} else if ("price_desc".equals(orderBy)) {
			productList.sort((p1, p2) -> p1.getPrice() >= p2.getPrice() ? -1 : 1);
		} else {
			
		}
		model.addAttribute("findList", productList);
		model.addAttribute("findSize", "検索結果:" + productList.size() + "件");
		return "menu";
	}
	
	@RequestMapping(value = "/servlet")
	public String logout(Model model) {
		session.invalidate();
		return "logout";
	}
}